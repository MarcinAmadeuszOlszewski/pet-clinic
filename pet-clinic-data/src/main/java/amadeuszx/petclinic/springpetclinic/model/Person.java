package amadeuszx.petclinic.springpetclinic.model;

public class Person  extends BaseEntity{
  private String firstName;
  private String lastName;

  private String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  private String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }
}
