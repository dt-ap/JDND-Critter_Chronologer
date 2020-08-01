package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

  @PersistenceContext
  private final EntityManager entityManager;
  private final PetRepository repository;

  public PetService(EntityManager entityManager, PetRepository repository) {
    this.entityManager = entityManager;
    this.repository = repository;
  }

  public Pet createPet(Pet pet, Long ownerId) {
    var customer = entityManager.getReference(Customer.class, ownerId);
    var newPet = repository.save(pet);
    customer.addPet(newPet);
    return newPet;
  }

  public Optional<Pet> getPetById(Long id) {
    return repository.findById(id);
  }

  public List<Pet> getManyByOwnerId(Long ownerId) {
    return repository.findAllByOwnerId(ownerId);
  }
}
