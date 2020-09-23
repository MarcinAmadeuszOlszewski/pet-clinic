package amadeuszx.petclinic.springpetclinic.bootstrap;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
import amadeuszx.petclinic.springpetclinic.services.map.OwnerServiceMap;
import amadeuszx.petclinic.springpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(final OwnerService ownerService, final VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(final String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Jan");
        owner1.setLastName("Jankowski");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Janina");
        owner2.setLastName("Janikowska");
        ownerService.save(owner2);

        System.out.println("Owners loaded");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Marian");
        vet1.setLastName("Marianski");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Marianna");
        vet2.setLastName("Marianowska");
        vetService.save(vet2);

        System.out.println("Vets loaded");
    }
}
