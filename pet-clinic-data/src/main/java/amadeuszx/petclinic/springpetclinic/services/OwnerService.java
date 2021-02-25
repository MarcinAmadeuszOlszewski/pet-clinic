package amadeuszx.petclinic.springpetclinic.services;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);
}
