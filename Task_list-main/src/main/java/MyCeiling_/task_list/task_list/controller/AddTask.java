package MyCeiling_.task_list.task_list.controller;


import MyCeiling_.task_list.task_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@Controller
public class AddTask {
    @Autowired
    TaskService ts;

    /**
     * Вывод формы для ввода заголовка и описания задачи
     *
     * @return - вывод страницы с формой ввода задачи
     */
    @GetMapping("/addtask")
    public String addTaskForm() {
        return "addtask";
    }

    /**
     * Обработчик POST запроса на создание задачи
     *
     * @param description - описание задачи
     * @return - переход на стартовую страницу
     */
    @PostMapping("/addtask")
    public String addTaskAction(@RequestParam String description,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline,
                                Model model) {
        ts.saveTask(ts.addTask(description, deadline));

        model.addAttribute("tasks", ts.getAllTasks());
        return "task_manager";
    }
}