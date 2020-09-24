package amadeuszx.petclinic.springpetclinic.bootstrap;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.model.PetType;
import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
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
    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Janina");
    owner2.setLastName("Janikowska");
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
