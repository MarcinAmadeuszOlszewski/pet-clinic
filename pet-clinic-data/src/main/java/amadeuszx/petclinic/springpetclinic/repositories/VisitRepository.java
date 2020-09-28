package amadeuszx.petclinic.springpetclinic.repositories;

import amadeuszx.petclinic.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
