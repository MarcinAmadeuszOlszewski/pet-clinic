package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Speciality;
import amadeuszx.petclinic.springpetclinic.services.SpecialityService;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("HashMapVesion")
public class SpecialtityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {

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
