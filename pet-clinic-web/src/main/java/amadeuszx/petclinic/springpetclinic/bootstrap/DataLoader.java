package amadeuszx.petclinic.springpetclinic.bootstrap;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.model.Pet;
import amadeuszx.petclinic.springpetclinic.model.PetType;
import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  public DataLoader(final OwnerService ownerService, final VetService vetService,
      final PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(final String... args) throws Exception {

    PetType dog = new PetType();
    dog.setName("dog");
    final PetType savedDog = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("cat");
    final PetType savedCat = petTypeService.save(cat);

    Owner owner1 = new Owner();
    owner1.setFirstName("Jan");
    owner1.setLastName("Jankowski");
    owner1.setAddress("1 Maja");
    owner1.setCity("Jonatowice");
    owner1.setTelephone("666-555-444");

    Pet pet11=new Pet();
    pet11.setPetType(savedDog);
    pet11.setOwner(owner1);
    pet11.setBirthDate(LocalDate.of(2019,1,1));
    pet11.setName("Jacus");
    owner1.getPets().add(pet11);
    Pet pet12=new Pet();
    pet12.setPetType(savedDog);
    pet12.setOwner(owner1);
    pet12.setBirthDate(LocalDate.of(2019,2,2));
    pet12.setName("Jacusia");
    owner1.getPets().add(pet12);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Janina");
    owner2.setLastName("Janikowska");
    owner2.setAddress("3 Maja");
    owner2.setCity("Jonatowice");
    owner2.setTelephone("666-555-333");

    Pet pet21=new Pet();
    pet21.setPetType(savedCat);
    pet21.setOwner(owner2);
    pet21.setBirthDate(LocalDate.of(2018,8,8));
    pet21.setName("Mruczka");
    owner2.getPets().add(pet21);

    ownerService.save(owner2);

    System.out.println("Owners loaded");

    Vet vet1 = new Vet();
    vet1.setFirstName("Marian");
    vet1.setLastName("Marianski");
    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Marianna");
    vet2.setLastName("Marianowska");
    vetService.save(vet2);

    System.out.println("Vets loaded");
  }
}
