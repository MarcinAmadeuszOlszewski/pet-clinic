package amadeuszx.petclinic.springpetclinic.model;

public class PetType extends BaseEntity{
private String name;

  private String getName() {
    return name;
  }

  private void setName(final String name) {
    this.name = name;
  }
}
