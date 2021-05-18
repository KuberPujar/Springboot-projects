package com.juno.restservices.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juno.restservices.entities.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,CrudRepository<Order, Long>{

}
