package amadeuszx.petclinic.springpetclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.mockito.ArgumentMatchers;
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
    Owner returOwner1 = Owner.builder().id(ID).lastName(LAST_NAME).build();
    Owner returOwner2 = Owner.builder().id(2L).lastName("Yoko").build();
    owners = new HashSet<>(Arrays.asList(returOwner1, returOwner2));

    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @Test
  void findOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"))
        .andExpect(model().attributeExists("owner"));
    verifyNoInteractions(ownerService);
  }

  @Test
  void processFindFormReturnMany() throws Exception {
    Owner o1 = Owner.builder().id(1L).build();
    Owner o2 = Owner.builder().id(2L).build();

    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(o1, o2));

    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));
  }

  @Test
  void processFindFormReturnOne() throws Exception {
    Owner o1 = Owner.builder().id(1L).build();

    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(o1));

    mockMvc.perform(get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
  }

  @Test
  void displayOwner() throws Exception {
    Owner o = Owner.builder().id(1L).build();
    when(ownerService.findById(anyLong())).thenReturn(o);
    mockMvc.perform(get("/owners/123"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
  }

  @Test
  void initCreationForm() throws Exception {
    mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

    verifyNoMoreInteractions(ownerService);
  }

  @Test
  void processCreationForm() throws Exception {
    Owner o = Owner.builder().id(1L).build();
    when(ownerService.save(any())).thenReturn(o);
    mockMvc.perform(post("/owners/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));
    verify(ownerService).save(ArgumentMatchers.any());
  }

  @Test
  void initUpdateOwnerForm() throws Exception {
    Owner o = Owner.builder().id(1L).build();
    when(ownerService.findById(anyLong())).thenReturn(o);
    mockMvc.perform(get("/owners/1/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));

    verifyNoMoreInteractions(ownerService);
  }

  @Test
  void processUpdateOwnerForm() throws Exception {
    Owner o = Owner.builder().id(1L).build();
    when(ownerService.save(any())).thenReturn(o);
    mockMvc.perform(post("/owners/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));
    verify(ownerService).save(ArgumentMatchers.any());
  }

}