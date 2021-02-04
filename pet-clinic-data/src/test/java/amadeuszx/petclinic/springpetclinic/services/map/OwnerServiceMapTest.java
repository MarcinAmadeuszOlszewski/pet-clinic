package amadeuszx.petclinic.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerServiceMapTest {

  OwnerServiceMap ownerServiceMap;

  private Long getId() {
    return 1L;
  }

  private final String lastName = "LastName";

  @BeforeEach
  void setUp() {
    ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
    final Owner owner = Owner.builder().build();
    owner.setId(getId());
    owner.setLastName(lastName);
    ownerServiceMap.save(owner);
  }

  @Test
  void findAll() {
    Set<Owner> result = ownerServiceMap.findAll();
    assertEquals(1, result.size());
  }

  @Test
  void deleteById() {
    ownerServiceMap.deleteById(getId());
    assertTrue(ownerServiceMap.findAll().isEmpty());
  }

  @Test
  void delete() {
    ownerServiceMap.delete(ownerServiceMap.findById(getId()));
    assertTrue(ownerServiceMap.findAll().isEmpty());
  }

  @Test
  void saveExistingId() {
    final Owner owner = Owner.builder().build();
    final long id = 2L;
    owner.setId(id);
    Owner result = ownerServiceMap.save(owner);
    assertEquals(id, result.getId());
  }

  @Test
  void saveNoId() {
    final Owner owner = Owner.builder().build();
    Owner result = ownerServiceMap.save(owner);
    assertNotNull(result);
    assertNotNull(result.getId());
  }


  @Test
  void findById() {
    Owner result = ownerServiceMap.findById(getId());
    assertEquals(getId(), result.getId());
  }

  @Test
  void findByLastNameExists() {
    final Owner result = ownerServiceMap.findByLastName(lastName);
    assertNotNull(result);
    assertEquals(getId(), result.getId());
  }
  @Test
  void findByLastNameNotExists() {
    final Owner result = ownerServiceMap.findByLastName("Indiana");
    assertNull(result);
  }
}