package com.acme.customers.api.rest.v1.resources;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.acme.customers.api.rest.v1.services.CustomerService;
import com.acme.customers.lib.v1.Customer;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerResource {

	@Inject
	private CustomerService service;

	@GET
	public Response getCustomers(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
			throws SQLException {
		return Response.ok(service.findAllCustomers(limit, offset)).header("X-Total-Count", 0).build();

	}

	@GET
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") String id) throws SQLException {
		return Response.ok(service.findCustomerById(id)).build();
	}

	@POST
	public Response createCustomer(Customer newCustomer) throws SQLException {
		return Response.ok(service.createCustomer(newCustomer)).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateCustomer(@PathParam("id") String id, Customer customer) throws SQLException {
		return Response.ok(service.updateCustomer(id, customer)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") String id) throws SQLException {
		service.deleteCustomerById(id);
		return Response.noContent().build();
	}
}
