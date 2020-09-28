package amadeuszx.petclinic.springpetclinic.repositories;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

  Owner findByLastName(String lastName);
}
