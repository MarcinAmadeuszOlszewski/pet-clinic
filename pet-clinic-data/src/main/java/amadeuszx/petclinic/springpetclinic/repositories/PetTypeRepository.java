package amadeuszx.petclinic.springpetclinic.repositories;

import amadeuszx.petclinic.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
