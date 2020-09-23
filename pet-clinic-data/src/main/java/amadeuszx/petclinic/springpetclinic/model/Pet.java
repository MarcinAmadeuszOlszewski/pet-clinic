package amadeuszx.petclinic.springpetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {

  private PetType petType;
  private Owner owner;
  private LocalDate birthDate;

  private PetType getPetType() {
    return petType;
  }

  private void setPetType(final PetType petType) {
    this.petType = petType;
  }

  private Owner getOwner() {
    return owner;
  }

  private void setOwner(final Owner owner) {
    this.owner = owner;
  }

  private LocalDate getBirthDate() {
    return birthDate;
  }

  private void setBirthDate(final LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}
