package com.saha.amit.repository;

import com.saha.amit.dto.CustomerDto;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> findByProfileIn(List<Profile> profiles);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "WHERE c.customerUuid = :id")
    Customer findCustomersById(@Param("id") Long id);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "LEFT JOIN FETCH c.orders " +
            "WHERE c.customerUuid = :id")
    Customer getCustomerProfileOrder(@Param("id") Long id);


    @Query("SELECT c FROM Customer c JOIN FETCH c.orders WHERE c.profile IN :profiles")
    List<Customer> findByProfileIn(@Param("profiles") List<Profile> profiles);


    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.orders " +
            "JOIN FETCH c.profile p " +
            "WHERE p.email LIKE %:email%")
    List<Customer> findByEmailContainingWithOrders(@Param("email") String email);



    //@Query("SELECT c FROM Customer c WHERE SIZE(c.orders) > 3")
    @Query("SELECT c FROM Customer c " +
            "JOIN c.profile p " +
            "JOIN c.orders o " +
            "GROUP BY c " +
            "HAVING COUNT(o) > 3")
    List<Customer> findCustomersWithMoreThanThreeOrders();

    @Query(value = "SELECT c.* FROM Customer c " +
            "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
            "JOIN Orders o ON c.customer_uuid = o.customer_id " +
            "GROUP BY c.customer_uuid " +
            "HAVING COUNT(o.order_uuid) > 3",
            nativeQuery = true)
    List<Customer> findCustomersWithMoreThanThreeOrdersNative();

    @Query("SELECT c FROM Customer c " +
            "JOIN c.orders o " +
            "JOIN o.payment p " +
            "WHERE p.paymentStatus = 'SUCCESS' " +
            "GROUP BY c.customerUuid " +
            "HAVING COUNT(o) > 3")
    List<Customer> findCustomersWithMoreThanThreeSuccessfulOrders();

}

