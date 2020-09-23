package amadeuszx.petclinic.springpetclinic.bootstrap;

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

  }
}
