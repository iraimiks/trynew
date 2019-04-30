package lv.raimiks.springvuejs28042019.controller;


import lv.raimiks.springvuejs28042019.model.Customer;
import lv.raimiks.springvuejs28042019.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        System.out.println("Get Customers ...");
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }
    @PostMapping("/customer")
    public Customer postCustumer(@RequestBody  Customer customer){
        Customer _customer = customerRepository.save(new Customer(customer.getName(),customer.getAge()));
        return _customer;
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id){
        System.out.println("Delete customer ID = "+id+" ...");
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Customer been deleted ", HttpStatus.OK);
    }
    @GetMapping("customer/age/{age}")
    public List<Customer> findByAge(@PathVariable int age){
        List<Customer> customers = customerRepository.findByAge(age);
        return customers;
    }
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody  Customer customer){
        System.out.println("Update customer with ID: "+id+"...");
        Optional<Customer> customerData = customerRepository.findById(id);
        if(customerData.isPresent()){
            Customer _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            _customer.setActive(customer.isActive());
            return new ResponseEntity<>(customerRepository.save(_customer),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
















