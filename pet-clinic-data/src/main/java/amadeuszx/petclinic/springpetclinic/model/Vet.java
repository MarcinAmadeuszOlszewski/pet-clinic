package amadeuszx.petclinic.springpetclinic.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vetd")
public class Vet extends Person {

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "vets_specialties",
      joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialties_id"))
  private Set<Speciality> specialities = new HashSet<>();

  public Set<Speciality> getSpecialities() {
    return specialities;
  }

  public void setSpecialities(final Set<Speciality> specialities) {
    this.specialities = specialities;
  }
}
