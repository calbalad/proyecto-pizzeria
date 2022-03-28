package com.pizzaiolo.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;

@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Order, Integer>{
//	List<Order> findByStatus(Status status);
	<T> List<T> findByIdOrderIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdOrderIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdOrderIsNotNull(Pageable pageable, Class<T> type);

}
