package ru.digitalchief.client_order.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.digitalchief.client_order.model.Order;
import ru.digitalchief.client_order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    default Optional<Order> findOrder(Order order) {
        Specification<Order> specification = Specification.where(descriptionIn(order.getDescription()))
                .and(amountIn(order.getAmount()))
                .and(orderDateIn(order.getOrderDate()))
                .and(orderStatusIn(order.getOrderStatus()));
        return findOne(specification);
    }

    default Specification<Order> descriptionIn(String description) {
        return attributeEquals("description", description);
    }

    default Specification<Order> amountIn(BigDecimal amount) {
        return (order, cq, cb) -> cb.equal(order.get("amount"), amount);
    }

    default Specification<Order> orderDateIn(LocalDateTime orderDate) {
        return (order, cq, cb) -> cb.equal(order.get("orderDate"), orderDate);
    }

    default Specification<Order> orderStatusIn(OrderStatus orderStatus) {
        return (order, cq, cb) -> cb.equal(order.get("orderStatus"), orderStatus);
    }

    private static Specification<Order> attributeEquals(String attributeName, String value) {
        return (order, cq, cb) -> isNotNull(value)
                ? cb.equal(cb.lower(order.get(attributeName)), value.toLowerCase()) : cb.conjunction();
    }

    private static boolean isNotNull(String line) {
        return Objects.nonNull(line);
    }
}