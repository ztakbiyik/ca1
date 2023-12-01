package com.assignment.dsystem;

import com.assignment.dsystem.model.Customer;
import com.assignment.dsystem.repository.CustomerRepository;
import com.assignment.dsystem.repository.CustomerRepository;
import com.assignment.dsystem.service.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {
	
	CustomerService customerService;

	public CustomerController(){
		customerService = new CustomerService();
	}

	@GET
	public Response getAll() {
		return Response.ok().entity(customerService.getAllCustomers()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") Long id) {
		return Response.ok().entity(customerService.getCustomerById(id)).build();
	}

	@GET
	@Path("/loan/{id}")
	public Response getCustomerLoan(@PathParam("id") Long id) {
		return Response.ok().entity(customerService.getCustomerById(id)).build();
	}
	
	@POST
	public Response insertCustomer(Customer customer) {
		return Response.ok().entity(customerService.createCustomer(customer)).build();
	}
	
	@PUT
	public Response updateCustomer(Customer customer) {
		return Response.ok().entity(customerService.updateCustomer(customer)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		return Response.ok().entity(customerService.deleteCustomerById(id)).build();
	}
}
