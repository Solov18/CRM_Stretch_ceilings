package MyCeiling_.task_list.task_list.repository;

import MyCeiling_.task_list.task_list.domain.Task;
import MyCeiling_.task_list.task_list.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * получить список задач по их статусу
     */
    List<Task> findByStatus(TaskStatus taskStatus); // по статусу
    List<Task> findByCreatedDate(LocalDateTime localDateTime); // по дате создания


}
