package com.saha.amit.service;

import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.Customer;
import com.saha.amit.model.Profile;
import com.saha.amit.repository.CustomerRepository;
import com.saha.amit.repository.ProfileRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProfileRepository profileRepository;


    private final Log log = LogFactory.getLog(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository, ProfileRepository profileRepository) {
        this.customerRepository = customerRepository;
        this.profileRepository = profileRepository;
    }


    public void save(Customer customer) {
        customerRepository.save(customer);
    }


    public Profile findProfileById(Long id){
        return  profileRepository.findById(id).orElse(null);
    }

    /*
    In this implementation we are not using default findById method provided by JPA instead we are using custom JPQL query. Reason
        - Customer class is mapped with Profile as @OneToOne(fetch = FetchType.LAZY),
        - Hence default method findById(id) will only fetch Customer first and when it encounters getProfile() then will fetch Customer,
          in a 2nd query leading to multiple query which is inefficient
        - If we make it eager then if in some scenarios we only need Customer it will still join and get Profile for e.g. we don't need Orders here
          So we have kept it lazy, and it's not called.
        - To see the differance make every thing EAGER and un-comment findById to see multiple un-necessary queries getting called in console.
        - Thumb rule keep every thing LAZY by default, unless you are absolutely sure and use findById when you only need Customer
     */
    public Customer findCustomersById(Long id){
        return customerRepository.findCustomersById(id);
        //return customerRepository.findById(id).orElse(null);
    }


    public CustomerDto findCustomersByIdProjections(Long id){
        return customerRepository.findCustomersByIdProjections(id);
    }

    public Customer findCustomerProfileOrderData(Long id){
        return customerRepository.findCustomerProfileOrderData(id);
    }

    public CustomerDto findCustomerProfileOrderDataNative(Long id) {
        List<Object[]> results = customerRepository.findCustomerProfileOrderDataNative(id);

        if (results.isEmpty()) {
            return null;
        }

        Object[] firstResult = results.get(0);

        ProfileDto profileDto = new ProfileDto(
                (Long) firstResult[2], (String) firstResult[3], (String) firstResult[4],
                (String) firstResult[5], new AddressDto((String) firstResult[6], (String) firstResult[7], (String) firstResult[8], (String) firstResult[9])
        );

        List<OrderDto> orderDtos = results.stream()
                .map(result -> new OrderDto((String) result[10], (String) result[11]))
                .collect(Collectors.toList());

        return new CustomerDto((Long) firstResult[0], (String) firstResult[1], profileDto, orderDtos);
    }


    public List<Customer> findByEmailContainingWithOrders(String email){
        log.info("findByEmailContaining -->"+ email);
        return customerRepository.findByEmailContainingWithOrders(email);
    }

    public List<Customer> findCustomersWithMoreThanThreeOrders(){
        if (new Random().nextBoolean()){
            log.info("findCustomersWithMoreThanThreeOrders JPA");
            return customerRepository.findCustomersWithMoreThanThreeOrders();
        }else {
            log.info("findCustomersWithMoreThanThreeOrdersNative");
            return customerRepository.findCustomersWithMoreThanThreeOrdersNative();
        }
    }


    public List<Customer> findCustomersWithMoreThanThreeSuccessfulOrders(){
        return customerRepository.findCustomersWithMoreThanThreeSuccessfulOrders();
    }
}
