package amadeuszx.petclinic.springpetclinic.services;

import amadeuszx.petclinic.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);
}
