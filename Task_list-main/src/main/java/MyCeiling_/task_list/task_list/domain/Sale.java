package MyCeiling_.task_list.task_list.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private Double amount;
    private LocalDate saleDate;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}