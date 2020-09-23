package amadeuszx.petclinic.springpetclinic.services;

import amadeuszx.petclinic.springpetclinic.model.Vet;
import java.util.Set;

public interface VetService {

  Vet findById(Long id);

  Vet save(Vet vet);

  Set<Vet> findAll();
}
