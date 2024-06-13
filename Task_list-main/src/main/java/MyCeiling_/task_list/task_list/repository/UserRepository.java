package MyCeiling_.task_list.task_list.repository;



import MyCeiling_.task_list.task_list.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}