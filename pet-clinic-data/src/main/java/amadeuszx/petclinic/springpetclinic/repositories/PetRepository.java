package amadeuszx.petclinic.springpetclinic.repositories;

import amadeuszx.petclinic.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
