package MyCeiling_.task_list.task_list.controller;

import MyCeiling_.task_list.task_list.service.TaskService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UpdateTask {

    @Autowired
    TaskService ts;

    /**
     * Обработка GET запроса за изменение статуса
     *
     * @param id - идентификатор задачи
     * @return - редирект на стартовую страницу
     */
    @GetMapping("/upd/{id}")
    public String updateTaskStatus(@PathVariable Long id) {
        ts.updateStatus(id);
        return "redirect:/tasks";
    }
}
