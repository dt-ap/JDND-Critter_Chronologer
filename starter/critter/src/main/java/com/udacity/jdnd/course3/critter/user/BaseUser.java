package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This is super class entity for employee and customer.
 */
@MappedSuperclass
public abstract class BaseUser extends BaseEntity {
  @Column(length = 100)
  protected String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
