package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Pet;
import amadeuszx.petclinic.springpetclinic.services.CrudService;
import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

  @Override
  public Set<Pet> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(final Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(final Pet object) {
    super.delete(object);
  }

  @Override
  public Pet save(final Pet object) {
    return super.save(object.getId(), object);
  }

  @Override
  public Pet findById(final Long id) {
    return super.findById(id);
  }
}
