package amadeuszx.petclinic.springpetclinic.controllers;


import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.model.Pet;
import amadeuszx.petclinic.springpetclinic.model.PetType;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.PetService;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

  private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
  private final PetService petService;
  private final OwnerService ownerService;
  private final PetTypeService petTypeService;

  public PetController(final PetService petService,
      final OwnerService ownerService, final PetTypeService petTypeService) {
    this.petService = petService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
  }


  @ModelAttribute("types")
  public Collection<PetType> populatePetTypes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable Long ownerId) {
    return ownerService.findById(ownerId);
  }

//  @ModelAttribute("owner")
//  public void initOwnerBinde(WebDataBinder dataBinder) {
//    dataBinder.setDisallowedFields("id");
//  }

  @GetMapping("/pets/new")
  public String initCreationForm(Owner owner, Model model) {
    Pet pet = new Pet();
    owner.getPets().add(pet);
    pet.setOwner(owner);
    model.addAttribute("pet", pet);
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/pets/new")
  public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model,@PathVariable("ownerId") Long ownerId) {
    if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
      result.rejectValue("name", "duplicate", "already exists");
    }
    owner.setId(ownerId);
    owner.getPets().add(pet);
    pet.setOwner(owner);
    if (result.hasErrors()) {
      model.put("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      petService.save(pet);

      return "redirect:/owners/" + owner.getId();
    }
  }

  @GetMapping("/pets/{petId}/edit")
  public String initUpdateForm(@PathVariable Long petId, Model model) {
    model.addAttribute("pet", petService.findById(petId));
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/pets/{petId}/edit")
  public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
    if (result.hasErrors()) {
      pet.setOwner(owner);
      model.addAttribute("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      owner.getPets().add(pet);
      petService.save(pet);
      return "redirect:/owners/" + owner.getId();
    }
  }
}
