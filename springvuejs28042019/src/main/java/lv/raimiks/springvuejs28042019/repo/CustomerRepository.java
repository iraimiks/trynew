package lv.raimiks.springvuejs28042019.repo;

import lv.raimiks.springvuejs28042019.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findByAge(int age);
}
