package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  private final CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  public List<Customer> getAll() {
    return repository.findAllWithPets();
  }

  public Customer getById(Long id) {
    return repository.findById(id).orElseThrow(RuntimeException::new);
  }

  public Customer create(Customer customer) {
    return repository.save(customer);
  }

  public Customer getOwnerByPetId(Long petId) {
    return repository.findByPetsId(petId).orElseThrow(RuntimeException::new);
  }
}
