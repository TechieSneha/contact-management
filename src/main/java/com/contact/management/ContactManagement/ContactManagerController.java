package com.contact.management.ContactManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author Sneha 
 * This class is responsible for creating the CRUD API for the contact management
 *
 */


@RestController
@RequestMapping(path = "/contacts")
public class ContactManagerController {

	@Autowired
	private ContactManagerRepository contactManagerRepository;

	/**
	 * 
	 * @param contactdetails
	 * @return response fo the query. Saved if success
	 */
	@PostMapping(path = "/addContactsdeatils")
	public @ResponseBody String addNewContacts(@RequestBody Contactdetails contactdetails) {

		/*
		 * System.out.println(" firstName " + contactdetails.getFirstName() +
		 * " lastName " + contactdetails.getLastName() + " emailId " +
		 * contactdetails.getEmailId() + " mobileNumber " +
		 * contactdetails.getMobileNumber());
		 */
		Contactdetails managementEntity = new Contactdetails();
		managementEntity.setFirstName(contactdetails.getFirstName());
		managementEntity.setLastName(contactdetails.getLastName());
		managementEntity.setEmailId(contactdetails.getEmailId());
		managementEntity.setMobileNumber(contactdetails.getMobileNumber());

		contactManagerRepository.save(managementEntity);

		return "Saved";

	}
    /**
     * 
     * @return all the contact details
     */

	@GetMapping(path = "/allContactsDetails")
	public @ResponseBody Iterable<Contactdetails> getAllContacts() {
		return contactManagerRepository.findAll();
	}
    /**
     * 
     * @param firstName
     * @return list of contacts filter by firstName
     */
	@GetMapping(path = "/allContactsDetails/firstname/{firstName}")
	public @ResponseBody List<Contactdetails> getContactDetailsByFirstName(@PathVariable String firstName) {
		List<Contactdetails> listOfContacts = new ArrayList<>();
		listOfContacts = contactManagerRepository.findByFirstName(firstName);
		return listOfContacts;

	}
    /**
     * 
     * @param lastName
     * @return list of contactsDetails filter by lastName
     */
	@GetMapping(path = "/allContactsDetails/lastName/{lastName}")
	public @ResponseBody List<Contactdetails> getContactBylastName(@PathVariable String lastName) {
		 return contactManagerRepository.findByLastName(lastName);
		
	}
	 /**
     * 
     * @param emailId
     * @return contactsDetails filter by emailId
     */
	@GetMapping(path = "/allContactsDetails/emailId/{emailId}")
	public @ResponseBody Contactdetails getContactsByEmail(@PathVariable String emailId) {
		return contactManagerRepository.findByEmailId(emailId);
	}
	
	/**
	 * 
	 * @param id
	 * delete contacts by Id
	 */
	@DeleteMapping(path="/deleteContactsDetails/{id}")
	public void deleteContacts(@PathVariable Integer id) {
		contactManagerRepository.deleteById(id);
	}
	/**
	 * 
	 * @param details
	 * @param id
	 * update the contact details by Id
	 * @return status
	 */
	@PutMapping(path = "/updateContactsDetails/{id}")
	public @ResponseBody String updateContactDetails(@RequestBody Contactdetails details, @PathVariable Integer id) {
		Contactdetails contactdetails = contactManagerRepository.findById(id).get();
		if(!details.getFirstName().isEmpty()) {
			contactdetails.setFirstName(details.getFirstName());
		}
		if(!details.getLastName().isEmpty()) {
			contactdetails.setLastName(details.getLastName());
		}
		if(!details.getEmailId().isEmpty()) {
			contactdetails.setEmailId(details.getEmailId());
		}
		if(!details.getMobileNumber().isEmpty()) {
			contactdetails.setMobileNumber(details.getMobileNumber());
		}
		contactManagerRepository.save(contactdetails);
		return "success";
	}

}
