package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 100)
  private String name;

  @Column(length = 18)
  private String phoneNumber;
  private String notes;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pet> pets = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  public void addPet(Pet pet) {
    pets.add(pet);
    pet.setOwner(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Customer customer = (Customer) o;

    return id.equals(customer.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
