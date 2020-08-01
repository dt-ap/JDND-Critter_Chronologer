package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("FROM Customer")
  @EntityGraph(attributePaths = "pets")
  List<Customer> findAllWithPets();

  Optional<Customer> findByPetsId(Long petId);
}
