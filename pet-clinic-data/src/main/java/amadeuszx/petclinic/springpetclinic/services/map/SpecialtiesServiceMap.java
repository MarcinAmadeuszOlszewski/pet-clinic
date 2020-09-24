package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Speciality;
import amadeuszx.petclinic.springpetclinic.services.SpecialtiesService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class SpecialtiesServiceMap extends AbstractMapService<Speciality, Long> implements SpecialtiesService {

  @Override
  public Set<Speciality> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Speciality object) {
    super.delete(object);
  }

  @Override
  public Speciality save(final Speciality object) {
    return super.save(object);
  }

  @Override
  public Speciality findById(final Long id) {
    return super.findById(id);
  }
}
