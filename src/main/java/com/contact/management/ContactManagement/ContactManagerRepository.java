package com.contact.management.ContactManagement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactManagerRepository extends CrudRepository<Contactdetails, Integer>{
   List<Contactdetails> findByFirstName(String firstName);
   List<Contactdetails> findByLastName(String lastName);
   Contactdetails findByEmailId(String emailId);
   List<Contactdetails> deleteByEmailId(String emailId);
   
}
