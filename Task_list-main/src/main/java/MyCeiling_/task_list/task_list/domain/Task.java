package MyCeiling_.task_list.task_list.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
@Data
@Entity
//@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq")
    @SequenceGenerator(name = "tasks_seq", sequenceName = "TASKS_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TaskStatus status;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "DEADLINE")
    private LocalDateTime deadline;
    @Transient // Чтобы это поле не сохранялось в базу данных
    private Duration timeUntilDeadline;

    // Метод для установки дедлайна

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        calculateTimeUntilDeadline();
    }

    private void calculateTimeUntilDeadline() {
        LocalDateTime now = LocalDateTime.now();
        if (deadline.isAfter(now)) {
            this.timeUntilDeadline = Duration.between(now, deadline);
        } else {
            this.timeUntilDeadline = Duration.ZERO;
            this.status = TaskStatus.EXPIRED;
        }
    }

    public String getTimeUntilDeadlineAsString() {
        long days = timeUntilDeadline.toDays();
        long hours = timeUntilDeadline.toHoursPart();
        long minutes = timeUntilDeadline.toMinutesPart();
        return String.format("%d days, %d hours, %d minutes", days, hours, minutes);
    }


}
