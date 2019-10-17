package com.acme.customers.api.rest.v1.services.impl;

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

import com.acme.customers.api.rest.v1.services.CustomerService;
import com.acme.customers.api.rest.v1.services.exceptions.EmptyPayloadException;
import com.acme.customers.api.rest.v1.services.exceptions.IdMismatchException;
import com.acme.customers.api.rest.v1.services.exceptions.ResourceNotFoundException;
import com.acme.customers.lib.v1.Customer;
import com.acme.customers.lib.v1.CustomerStatus;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

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

	@Override
	public Customer findCustomerById(String id) throws SQLException {
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

			return customer;
		}

		throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
	}

	@Override
	public List<Customer> findAllCustomers(Integer limit, Integer offset) throws SQLException {
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

		return customers;
	}

	@Override
	public Customer createCustomer(Customer customer) throws SQLException {
		if (customer == null) {
			throw new EmptyPayloadException(Customer.class.getSimpleName());
		}
		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement("INSERT INTO customers "
				+ "(id, first_name, last_name, email, date_of_birth, status, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		String id = UUID.randomUUID().toString();

		stmt.setString(1, id);
		stmt.setString(2, customer.getFirstName());
		stmt.setString(3, customer.getLastName());
		stmt.setString(4, customer.getEmail());

		if (customer.getDateOfBirth() != null) {

			stmt.setTimestamp(5, new Timestamp(customer.getDateOfBirth().getTime()));
		} else {
			stmt.setNull(5, Types.DATE);
		}

		if (customer.getStatus() != null) {

			stmt.setString(6, customer.getStatus().toString());
		}

		Timestamp timestampNow = new Timestamp(new Date().getTime());

		stmt.setTimestamp(7, timestampNow);
		stmt.setTimestamp(8, timestampNow);

		stmt.executeUpdate();

		customer.setId(id);
		customer.setUpdatedAt(timestampNow);
		customer.setCreatedAt(timestampNow);

		con.close();

		return customer;
	}

	@Override
	public Customer updateCustomer(String id, Customer customer) throws SQLException {
		if (customer == null) {
			throw new EmptyPayloadException(Customer.class.getSimpleName());
		}

		if (!id.equals(customer.getId())) {
			throw new IdMismatchException(id, customer.getId());
		}

		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM customers WHERE ID = ?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
		}

		Timestamp createdAt = rs.getTimestamp("created_at");

		stmt = con.prepareStatement("UPDATE customers SET first_name = ?, last_name = ?,"
				+ "email = ?, date_of_birth = ?, status = ?, updated_at = ? WHERE id = ?");

		stmt.setString(1, customer.getFirstName());
		stmt.setString(2, customer.getLastName());
		stmt.setString(3, customer.getEmail());

		if (customer.getDateOfBirth() != null) {

			stmt.setTimestamp(4, new Timestamp(customer.getDateOfBirth().getTime()));
		} else {
			stmt.setNull(4, Types.DATE);
		}

		if (customer.getStatus() != null) {

			stmt.setString(5, customer.getStatus().toString());
		}

		Timestamp timestamp = new Timestamp(new Date().getTime());
		stmt.setTimestamp(6, timestamp);

		stmt.setString(7, id);

		stmt.executeUpdate();

		con.close();

		customer.setCreatedAt(createdAt);
		customer.setUpdatedAt(timestamp);

		return customer;
	}

	@Override
	public void deleteCustomerById(String id) throws SQLException {

		Connection con = dataSource.getConnection();
		PreparedStatement stmt = con.prepareStatement("DELETE FROM customers WHERE ID = ?");

		stmt.setString(1, id);

		stmt.executeUpdate();

		con.close();

	}

}
