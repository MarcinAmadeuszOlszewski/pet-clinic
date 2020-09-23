package amadeuszx.petclinic.springpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

  private Long id;

  public Long getId() {
    return id;
  }

  private void setId(final Long id) {
    this.id = id;
  }
}
