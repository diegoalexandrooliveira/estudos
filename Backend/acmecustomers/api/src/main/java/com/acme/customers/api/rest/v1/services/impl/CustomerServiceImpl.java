package com.acme.customers.api.rest.v1.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.acme.customers.api.mappers.CustomerMapper;
import com.acme.customers.api.models.db.CustomerEntity;
import com.acme.customers.api.rest.v1.services.CustomerService;
import com.acme.customers.api.rest.v1.services.exceptions.EmptyPayloadException;
import com.acme.customers.api.rest.v1.services.exceptions.IdMismatchException;
import com.acme.customers.api.rest.v1.services.exceptions.ResourceNotFoundException;
import com.acme.customers.lib.v1.Customer;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Customer findCustomerById(String id) throws SQLException {

		CustomerEntity customerEntity = em.find(CustomerEntity.class, id);
		if (customerEntity != null)
			return CustomerMapper.toCustomer(customerEntity);

		throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
	}

	@Override
	public List<Customer> findAllCustomers(Integer limit, Integer offset) throws SQLException {

		TypedQuery<CustomerEntity> query = em.createNamedQuery("CustomerEntity.findAll", CustomerEntity.class);
		Optional<Integer> opLimit = Optional.ofNullable(limit);
		Optional<Integer> opOffset = Optional.ofNullable(offset);
		query = query.setMaxResults(opLimit.orElse(99999999));
		query = query.setFirstResult(opOffset.orElse(0));

		List<CustomerEntity> customerEntities = query.getResultList();

		return customerEntities.stream().map(CustomerMapper::toCustomer).collect(Collectors.toList());

	}

	@Override
	public Customer createCustomer(Customer customer) throws SQLException {
		if (customer == null) {
			throw new EmptyPayloadException(Customer.class.getSimpleName());
		}

		customer.setId(null);
		CustomerEntity entity = CustomerMapper.toCustomerEntity(customer);
		em.persist(entity);

		return CustomerMapper.toCustomer(entity);
	}

	@Override
	public Customer updateCustomer(String id, Customer customer) throws SQLException {
		if (customer == null) {
			throw new EmptyPayloadException(Customer.class.getSimpleName());
		}

		if (!id.equals(customer.getId())) {
			throw new IdMismatchException(id, customer.getId());
		}

		CustomerEntity originalEntity = em.find(CustomerEntity.class, id);

		if (originalEntity == null)
			throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);

		CustomerEntity entity = CustomerMapper.toCustomerEntity(customer);
		entity.setId(id);
		entity.setCreatedAt(originalEntity.getCreatedAt());
		entity = em.merge(entity);

		return CustomerMapper.toCustomer(entity);
	}

	@Override
	public void deleteCustomerById(String id) throws SQLException {
		CustomerEntity originalEntity = em.find(CustomerEntity.class, id);

		if (originalEntity == null)
			throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);

		em.remove(originalEntity);

	}

}
