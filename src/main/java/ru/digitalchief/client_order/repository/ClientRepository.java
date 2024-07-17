package ru.digitalchief.client_order.repository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.digitalchief.client_order.model.Client;
import ru.digitalchief.client_order.model.Order;

import java.util.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    default Optional<Client> findClient(Client client) {
        Specification<Client> specification = Specification.where(nameIn(client.getName()))
                .and(phoneNumberIn(client.getPhoneNumber()))
                .and(addressIn(client.getAddress()))
                .and(emailIn(client.getEmail()))
                .and(ordersIn(client.getOrders()));
        return findOne(specification);
    }

    default Specification<Client> nameIn(String name) {
        return attributeEquals("name", name);
    }

    default Specification<Client> phoneNumberIn(String phoneNumber) {
        return attributeEquals("phoneNumber", phoneNumber);
    }

    default Specification<Client> addressIn(String address) {
        return attributeEquals("address", address);
    }

    default Specification<Client> emailIn(String email) {
        return attributeEquals("email", email);
    }

    default Specification<Client> ordersIn(Set<Order> orders) {
        return (client, cq, cb) -> {
            if (isNotEmpty(orders)) {
                List<Predicate> predicates = new ArrayList<>();
                for (Order order : orders) {
                    Subquery<Long> sq = cq.subquery(Long.class);
                    Root<Client> clientRoot = sq.correlate(client);
                    Join<Client, Order> ordersJoin = clientRoot.join("orders");
                    sq.select(clientRoot.get("id"))
                            .where(cb.equal(ordersJoin.get("id"), order.getId()));
                    predicates.add(cb.exists(sq));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            } else {
                return cb.conjunction();
            }
        };
    }

    private static Specification<Client> attributeEquals(String attributeName, String value) {
        return (client, cq, cb) -> isNotNull(value)
                ? cb.equal(cb.lower(client.get(attributeName)), value.toLowerCase()) : cb.conjunction();
    }

    private static boolean isNotNull(String line) {
        return Objects.nonNull(line);
    }

    private static boolean isNotEmpty(Collection<?> collection) {
        return Objects.nonNull(collection) && !collection.isEmpty();
    }
}