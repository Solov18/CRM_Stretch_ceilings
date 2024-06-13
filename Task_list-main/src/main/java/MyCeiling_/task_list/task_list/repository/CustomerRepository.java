package MyCeiling_.task_list.task_list.repository;


import MyCeiling_.task_list.task_list.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
