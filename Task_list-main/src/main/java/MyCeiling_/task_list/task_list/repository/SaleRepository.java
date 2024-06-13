package MyCeiling_.task_list.task_list.repository;


import MyCeiling_.task_list.task_list.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}