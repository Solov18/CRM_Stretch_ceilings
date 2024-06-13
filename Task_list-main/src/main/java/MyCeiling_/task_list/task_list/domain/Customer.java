package MyCeiling_.task_list.task_list.domain;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;


}