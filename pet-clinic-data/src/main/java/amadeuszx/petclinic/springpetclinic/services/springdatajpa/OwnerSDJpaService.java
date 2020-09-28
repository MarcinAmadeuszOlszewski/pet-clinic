package amadeuszx.petclinic.springpetclinic.services.springdatajpa;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.repositories.OwnerRepository;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

  private final OwnerRepository ownerRepository;

  public OwnerSDJpaService(final OwnerRepository ownerRepository) {this.ownerRepository = ownerRepository;}


  @Override
  public Owner findByLastName(final String lastName) {
    return ownerRepository.findByLastName(lastName);
  }

  @Override
  public Set<Owner> findAll() {
    Set<Owner> owners = new HashSet<>();
    ownerRepository.findAll().forEach(owners::add);
    return owners;
  }

  @Override
  public Owner findById(final Long id) {
    return ownerRepository.findById(id).orElse(null);
  }

  @Override
  public Owner save(final Owner object) {
    return ownerRepository.save(object);
  }

  @Override
  public void delete(final Owner object) {
    ownerRepository.delete(object);
  }

  @Override
  public void deleteById(final Long id) {
    ownerRepository.deleteById(id);
  }
}
