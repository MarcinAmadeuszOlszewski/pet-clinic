package amadeuszx.petclinic.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.repositories.OwnerRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

  public static final String LAST_NAME = "Smith";
  public static final long ID = 1L;
  @Mock
  OwnerRepository ownerRepository;
  @InjectMocks
  OwnerSDJpaService ownerSDJpaService;
  Owner returOwner;

  @BeforeEach
  void setUp() {
    returOwner = Owner.builder().build();
    returOwner.setId(ID);
    returOwner.setLastName(LAST_NAME);
  }

  @Test
  void findByLastName() {
    when(ownerRepository.findByLastName(any())).thenReturn(returOwner);
    Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);
    assertEquals(LAST_NAME, smith.getLastName());
  }

  @Test
  void findAll() {
    Owner returOwner2 = Owner.builder().build();
    returOwner2.setId(ID);
    returOwner2.setLastName(LAST_NAME);
    Set<Owner> owners = new HashSet<>(Arrays.asList(returOwner, returOwner2));
    when(ownerRepository.findAll()).thenReturn(owners);

    final Set<Owner> all = ownerSDJpaService.findAll();

    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returOwner));
    final Owner result = ownerSDJpaService.findById(ID);
    assertNotNull(result);
  }

  @Test
  void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
    final Owner result = ownerSDJpaService.findById(ID);
    assertNull(result);
  }

  @Test
  void save() {
    when(ownerRepository.save(any())).thenReturn(returOwner);
    final Owner result = ownerSDJpaService.save(returOwner);
    assertNotNull(result);
  }

  @Test
  void delete() {
    ownerSDJpaService.delete(returOwner);
    verify(ownerRepository).delete(any());
  }

  @Test
  void deleteById() {
    ownerSDJpaService.deleteById(ID);
    verify(ownerRepository).deleteById(anyLong());
  }
}