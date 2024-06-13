package MyCeiling_.task_list.task_list.service;

import MyCeiling_.task_list.task_list.domain.Task;

import MyCeiling_.task_list.task_list.domain.TaskStatus;
import MyCeiling_.task_list.task_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static MyCeiling_.task_list.task_list.domain.TaskStatus.COMPLETED;
import static MyCeiling_.task_list.task_list.domain.TaskStatus.IN_PROGRESS;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /** Записать задачу в базу
     * @param task - задача для записи
     */
    public Task saveTask(Task task) {     // сохранить задачу
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() { // получить все задачи
        return taskRepository.findAll();
    }

    // добавить задачу
    public  Task addTask(String description, LocalDateTime deadline ) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(TaskStatus.NOT_STARTED);
        task.setCreatedDate(LocalDateTime.now());

        task.setDeadline(deadline);
        return taskRepository.save(task);
     }
//     public void deleteTask(Long id) { // удалить задачу
//         Optional<Task> tasks = taskRepository.findById(id);
//         if (tasks.isPresent()) {
//             Task task = tasks.get();
//             if (task.getStatus().equals(COMPLETED)) { // Проверяем, имеет ли задача статус COMPLETED
//                 taskRepository.deleteById(id);
//             }
//             else { throw new IllegalStateException("Невозможно удалить незавершенную задачу.");
//         }
//
//     }
//         else {
//
//
//// Если задачи с заданным id не существует бросить исключение
//             throw new NoSuchElementException("Задача с id " + id + " не найдена.");
//         }
//     }
    public void delTask(Long id) {
        if (taskRepository.existsById(id))
            taskRepository.deleteById(id);
    }

     public List<Task> getTaskByStatus(TaskStatus taskStatus) { // получить задачи по статусу
         return taskRepository.findByStatus(taskStatus);}


    public void updateStatus(Long id) {
        if (taskRepository.existsById(id)) {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                switch (task.getStatus()) {
                    case NOT_STARTED -> {
                        task.setStatus(IN_PROGRESS);
                    }
                    case IN_PROGRESS -> {
                        task.setStatus(COMPLETED);
                    }
                    default -> {
                        return;
                    }
                }
            }

            taskRepository.save(task);
        }
    }
}




