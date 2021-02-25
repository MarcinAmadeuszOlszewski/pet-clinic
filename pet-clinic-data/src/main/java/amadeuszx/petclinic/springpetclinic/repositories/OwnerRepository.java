package amadeuszx.petclinic.springpetclinic.repositories;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findByLastNameLike(String lastName);
}
