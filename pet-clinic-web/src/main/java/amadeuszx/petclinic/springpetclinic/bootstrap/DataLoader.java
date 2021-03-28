package amadeuszx.petclinic.springpetclinic.bootstrap;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.model.Pet;
import amadeuszx.petclinic.springpetclinic.model.PetType;
import amadeuszx.petclinic.springpetclinic.model.Speciality;
import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.model.Visit;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import amadeuszx.petclinic.springpetclinic.services.SpecialityService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
import amadeuszx.petclinic.springpetclinic.services.VisitService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialityService specialityService;
  private final VisitService visitService;

  public DataLoader(final OwnerService ownerService, final VetService vetService,
      final PetTypeService petTypeService,
      final SpecialityService specialityService, VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialityService = specialityService;
    this.visitService = visitService;
  }

  @Override
  public void run(final String... args) throws Exception {
    final boolean isVets = vetService.findAll().isEmpty();
    if (isVets) {
      loadData();
    }
  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("dog");
    final PetType savedDog = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("cat");
    final PetType savedCat = petTypeService.save(cat);

    Owner owner1 = Owner.builder()
        .firstName("Jan")
        .lastName("Jankowski")
        .address("1 Maja")
        .city("Jonatowice")
        .telephone("666-555-444")
        .build();

    Pet pet11 = new Pet();
    pet11.setPetType(savedDog);
    pet11.setOwner(owner1);
    pet11.setBirthDate(LocalDate.of(2019, 1, 1));
    pet11.setName("Jacus");
    owner1.getPets().add(pet11);
    Pet pet12 = new Pet();
    pet12.setPetType(savedDog);
    pet12.setOwner(owner1);
    pet12.setBirthDate(LocalDate.of(2019, 2, 2));
    pet12.setName("Jacusia");
    owner1.getPets().add(pet12);

    ownerService.save(owner1);

    Owner owner2 = Owner.builder()
        .firstName("Janina")
        .lastName("Janikowska")
        .address("3 Maja")
        .city("Jonatowice")
        .telephone("666-555-333")
        .build();

    Pet pet21 = new Pet();
    pet21.setPetType(savedCat);
    pet21.setOwner(owner2);
    pet21.setBirthDate(LocalDate.of(2018, 8, 8));
    pet21.setName("Mruczka");
    owner2.getPets().add(pet21);
    ownerService.save(owner2);

    Visit catVisit = new Visit();
    catVisit.setPet(pet21);
    catVisit.setDate(LocalDate.now());
    catVisit.setDescription("Strasznie kicha");
    visitService.save(catVisit);

    System.out.println("Owners loaded");

    Speciality radiology = new Speciality();
    radiology.setDescription("radiologia");
    final Speciality saveRadiology = specialityService.save(radiology);
    Speciality surgery = new Speciality();
    surgery.setDescription("chirurgia");
    final Speciality saveSurgery = specialityService.save(surgery);
    Speciality dentistry = new Speciality();
    dentistry.setDescription("stomatologia");
    final Speciality saveDentistry = specialityService.save(dentistry);

    Vet vet1 = new Vet();
    vet1.setFirstName("Marian");
    vet1.setLastName("Marianski");
    vet1.getSpecialities().add(saveDentistry);
    vet1.getSpecialities().add(saveSurgery);
    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Marianna");
    vet2.setLastName("Marianowska");
    vet2.getSpecialities().add(saveRadiology);
    vet2.getSpecialities().add(saveSurgery);
    vetService.save(vet2);

    System.out.println("Vets loaded");
  }
}
