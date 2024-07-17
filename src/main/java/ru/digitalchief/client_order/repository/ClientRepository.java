package ru.digitalchief.client_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalchief.client_order.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}