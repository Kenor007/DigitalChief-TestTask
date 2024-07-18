package ru.digitalchief.client_order.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.digitalchief.client_order.model.Client;

import java.util.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    default Optional<Client> findClient(Client client) {
        Specification<Client> specification = Specification.where(nameIn(client.getName()))
                .and(phoneNumberIn(client.getPhoneNumber()))
                .and(addressIn(client.getAddress()))
                .and(emailIn(client.getEmail()));
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

    private static Specification<Client> attributeEquals(String attributeName, String value) {
        return (client, cq, cb) -> isNotNull(value)
                ? cb.equal(cb.lower(client.get(attributeName)), value.toLowerCase()) : cb.conjunction();
    }

    private static boolean isNotNull(String line) {
        return Objects.nonNull(line);
    }
}