package com.acme.customers.api.rest.v1.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.acme.customers.api.rest.v1.services.exceptions.EmptyPayloadException;
import com.acme.customers.api.rest.v1.services.exceptions.ResourceNotFoundException;
import com.acme.customers.lib.v1.Customer;
import com.acme.customers.lib.v1.CustomerStatus;
import com.acme.customers.lib.v1.response.CustomerList;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerResource {

	@Resource(lookup = "java:global/CustomersDS")
	private DataSource dataSource;

	@PostConstruct
	private void init() {

		Connection con = null;

		try {
			con = dataSource.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("select * from information_schema.tables where table_name = 'customers'");

			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				stmt = con.prepareStatement("CREATE TABLE customers("
						+ "id varchar(36) primary key, first_name varchar(255), last_name varchar(255), "
						+ "email varchar(255), status varchar(255), date_of_birth TIMESTAMP, "
						+ "updated_at TIMESTAMP, created_at TIMESTAMP)");

				stmt.executeUpdate();

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {

			try {
				if (con != null)
					con.close();
			} catch (SQLException ignored) {
			}
		}
	}

	@GET
	public Response getCustomers(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
			throws SQLException {

		List<Customer> customers = new ArrayList<>();

		String query = "SELECT * FROM customers ORDER BY id";

		if (limit != null && limit > 0) {

			query += " LIMIT " + limit;
		}

		if (offset != null && offset > 0) {

			query += " OFFSET " + offset;
		}

		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Customer customer = new Customer();
			customer.setId(rs.getString("id"));
			customer.setUpdatedAt(rs.getDate("updated_at"));
			customer.setCreatedAt(rs.getDate("created_at"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setDateOfBirth(rs.getTimestamp("date_of_birth"));
			customer.setStatus(CustomerStatus.valueOf(rs.getString("status")));

			customers.add(customer);
		}

		con.close();

		CustomerList customerList = new CustomerList();
		customerList.setCustomers(customers);

		return Response.ok(customerList).header("X-Total-Count", 0).build();
	}

	@GET
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") String id) throws SQLException {

		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM customers WHERE id = ? ORDER BY id");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			Customer customer = new Customer();
			customer.setId(rs.getString("id"));
			customer.setUpdatedAt(rs.getDate("updated_at"));
			customer.setCreatedAt(rs.getDate("created_at"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setDateOfBirth(rs.getDate("date_of_birth"));
			customer.setStatus(CustomerStatus.valueOf(rs.getString("status")));

			con.close();

			return Response.ok(customer).build();
		}

		throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
	}

	@POST
	public Response createCustomer(Customer newCustomer) throws SQLException {
		if (newCustomer == null) {
			throw new EmptyPayloadException(Customer.class.getSimpleName());
		}
		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement("INSERT INTO customers "
				+ "(id, first_name, last_name, email, date_of_birth, status, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		String id = UUID.randomUUID().toString();

		stmt.setString(1, id);
		stmt.setString(2, newCustomer.getFirstName());
		stmt.setString(3, newCustomer.getLastName());
		stmt.setString(4, newCustomer.getEmail());

		if (newCustomer.getDateOfBirth() != null) {

			stmt.setTimestamp(5, new Timestamp(newCustomer.getDateOfBirth().getTime()));
		} else {
			stmt.setNull(5, Types.DATE);
		}

		if (newCustomer.getStatus() != null) {

			stmt.setString(6, newCustomer.getStatus().toString());
		}

		Timestamp timestampNow = new Timestamp(new Date().getTime());

		stmt.setTimestamp(7, timestampNow);
		stmt.setTimestamp(8, timestampNow);

		stmt.executeUpdate();

		newCustomer.setId(id);
		newCustomer.setUpdatedAt(timestampNow);
		newCustomer.setCreatedAt(timestampNow);

		con.close();

		return Response.ok(newCustomer).build();
	}
}
