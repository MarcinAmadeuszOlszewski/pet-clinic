package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.PetService;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

  private final PetTypeService petTypeService;
  private final PetService petService;

  private OwnerServiceMap(final PetTypeService petTypeService,
      final PetService petService) {
    this.petTypeService = petTypeService;
    this.petService = petService;
  }

  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Owner object) {
    super.delete(object);
  }

  @Override
  public Owner save(final Owner object) {
    if (object != null) {
      if (object.getPets() != null) {
        object.getPets().forEach(pet -> {
          if (pet.getPetType() != null) {
            if (pet.getPetType().getId() == null) {
              pet.setPetType(petTypeService.save(pet.getPetType()));
            }
          } else {
            throw new RuntimeException("Required petType");
          }

          if (pet.getId() == null) {
            pet.setId(petService.save(pet).getId());
          }
        });
      }
    }

    return super.save(object);
  }

  @Override
  public Owner findById(final Long id) {
    return super.findById(id);
  }

  @Override
  public Owner findByLastName(final String lastName) {
    return null;
  }
}
