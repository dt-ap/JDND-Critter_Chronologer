package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

  private final PetRepository repository;
  private final CustomerRepository customerRepo;

  public PetService(PetRepository repository, CustomerRepository customerRepo) {
    this.repository = repository;
    this.customerRepo = customerRepo;
  }

  public Pet createPet(Pet pet, Long ownerId) throws RuntimeException {
    var customer = customerRepo.findById(ownerId)
        .orElseThrow(() -> new RuntimeException("Customer with id: '" + ownerId + "' not found."));
    var savedPet = repository.save(pet);
    customer.addPet(savedPet);
    return savedPet;
  }

  public Optional<Pet> getPetById(Long id) {
    return repository.findById(id);
  }

  public List<Pet> getManyByOwnerId(Long ownerId) {
    return repository.findAllByOwnerId(ownerId);
  }
}
