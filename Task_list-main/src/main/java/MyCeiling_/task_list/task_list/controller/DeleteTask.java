package MyCeiling_.task_list.task_list.controller;

import MyCeiling_.task_list.task_list.service.TaskService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log
public class DeleteTask {
    @Autowired
    TaskService ts;

    /**
     * Обработчик GET запроса на удаление задачи
     *
     * @param id - идентификатор задачи
     * @return - редирект на "/"
     */
    @GetMapping("/deltask/{id}")
    public ModelAndView delTask(@PathVariable Long id) {
        ts.delTask(id);
        return new ModelAndView("redirect:/tasks");
    }
}