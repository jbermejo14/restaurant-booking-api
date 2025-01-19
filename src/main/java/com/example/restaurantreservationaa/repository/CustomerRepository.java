package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAll();
    List<Customer> findByEmail(String email);
    List<Customer> findByNameAndEmail(String name, String email);
    List<Customer> findByNameAndEmailAndRole(String name, String email, String role);

    // JPQL Queries
    @Query("SELECT c FROM customers c WHERE c.name = :name")
    List<Customer> findByName(@Param("name") String name);

    @Query("SELECT COUNT(c) FROM customers c WHERE c.role = :role")
    long countByRole(@Param("role") String role);

    @Query("SELECT c FROM customers c WHERE c.dateJoined > :date")
    List<Customer> findCustomersJoinedAfter(@Param("date") Date date);

    // Native SQL Queries
    @Query(value = "SELECT * FROM customers WHERE name = :name", nativeQuery = true)
    List<Customer> findByNameNative(@Param("name") String name);

    @Query(value = "SELECT COUNT(*) FROM customers WHERE role = :role", nativeQuery = true)
    long countByRoleNative(@Param("role") String role);

    @Query(value = "SELECT * FROM customers WHERE date_joined > :date", nativeQuery = true)
    List<Customer> findCustomersJoinedAfterNative(@Param("date") Date date);

}
