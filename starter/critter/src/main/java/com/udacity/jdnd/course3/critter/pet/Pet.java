package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.domain.BaseEntity;
import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet extends BaseEntity {
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private PetType type;

  @Column(length = 100)
  private String name;
  private LocalDate birthDate;
  private String notes;

  @ManyToOne(fetch = FetchType.LAZY)
  private Customer owner;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PetType getType() {
    return type;
  }

  public void setType(PetType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Customer getOwner() {
    return owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pet pet = (Pet) o;

    return id.equals(pet.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
