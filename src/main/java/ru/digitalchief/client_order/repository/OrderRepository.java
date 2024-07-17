package ru.digitalchief.client_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalchief.client_order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}