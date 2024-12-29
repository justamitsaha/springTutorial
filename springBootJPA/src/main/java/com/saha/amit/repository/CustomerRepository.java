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

    @Query("SELECT c FROM Customer c JOIN FETCH c.orders WHERE c.profile IN :profiles")
    List<Customer> findByProfileIn(@Param("profiles") List<Profile> profiles);

    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "WHERE c.customerUuid = :id")
    Customer findCustomersById(@Param("id") Long id);

    @Query("SELECT new com.saha.amit.dto.CustomerDto(" +
            "c.customerUuid, c.customerName, " +
            "new com.saha.amit.dto.ProfileDto(p.profileUuid, p.email, p.name, p.phoneNumber)) " +
            "FROM Customer c " +
            "JOIN c.profile p " +
            "WHERE c.customerUuid = :id")
    CustomerDto findCustomersByIdProjections(@Param("id") Long id);


    /**
     * This query fetches unwanted payments as well
     * If JOIN is used instead of JOIN FETCH Orders also fetched from separate query
     * @param id Customer id
     * @return Customer
     */
    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "LEFT JOIN FETCH c.orders " +
            "WHERE c.customerUuid = :id")
    Customer findCustomerProfileOrderData(@Param("id") Long id);

    @Query(value = "SELECT c.customer_uuid AS customerUuid, c.customer_name AS customerName, " +
            "p.profile_uuid AS profileUuid, p.email, p.name, p.phone_number AS phoneNumber, " +
            "p.street, p.city, p.state, p.zip_code, " +
            "o.order_uuid AS orderUuid, o.order_number AS orderNumber " +
            "FROM Customer c " +
            "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
            "LEFT JOIN Orders o ON c.customer_uuid = o.customer_id " +
            "WHERE c.customer_uuid = :id",
            nativeQuery = true)
    List<Object[]> findCustomerProfileOrderDataNative(@Param("id") Long id);

/*    @Query("SELECT new com.saha.amit.dto.CustomerDto(" +
            "c.customerUuid, c.customerName, " +
            "new com.saha.amit.dto.customerProfileOrder.ProfileWithOutCustomer(p.profileUuid, p.email, p.name, p.phoneNumber, p.address), " +
            "(SELECT new com.saha.amit.dto.customerProfileOrder.OrderWithOutPaymentDto(o.orderUuid, o.orderNumber) " +
            "FROM Orders o WHERE o.customer.customerUuid = c.customerUuid)) " +
            "FROM Customer c " +
            "JOIN c.profile p " +
            "LEFT JOIN c.orders o " +
            "WHERE c.customerUuid = :id")
    CustomerDto getCustomerProfileOrder1(@Param("id") Long id);*/


    @Query("SELECT c FROM Customer c " +
            "JOIN FETCH c.profile p " +
            "JOIN FETCH c.orders " +
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

