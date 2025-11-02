package max_code.todolist.Entities;


import jakarta.persistence.*;
import lombok.*;
import max_code.todolist.Enums.Priority;
import max_code.todolist.Enums.Status;

@Entity
@Table(name = "todos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String comments;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Enumerated(EnumType.STRING)
    private Priority priority;

}
