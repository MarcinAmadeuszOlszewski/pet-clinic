package amadeuszx.petclinic.springpetclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import amadeuszx.petclinic.springpetclinic.model.Owner;
import amadeuszx.petclinic.springpetclinic.services.OwnerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  public static final String LAST_NAME = "Smith";
  public static final long ID = 1L;
  @Mock
  OwnerService ownerService;
  @InjectMocks
  OwnerController ownerController;
  MockMvc mockMvc;
  Set<Owner> owners;

  @BeforeEach
  void setUp() {
    Owner returOwner1 = Owner.builder().build();
    returOwner1.setId(ID);
    returOwner1.setLastName(LAST_NAME);
    Owner returOwner2 = Owner.builder().build();
    returOwner2.setId(2L);
    returOwner2.setLastName("Yoko");
    owners = new HashSet<>(Arrays.asList(returOwner1, returOwner2));

    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @Test
  void listVets() throws Exception {
    when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index"))
        .andExpect(model().attribute("owners", hasSize(2)));
  }

  @Test
  void findOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("notImplemented"));
    verifyNoInteractions(ownerService);
  }
}