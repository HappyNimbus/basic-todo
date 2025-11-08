package max_code.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import max_code.todolist.Enums.Priority;
import max_code.todolist.Enums.Status;

@Data
@AllArgsConstructor
public class ToDoRequest {
    private Long id;
    private String name;
    private String description;
    private String comments;
    private Priority priority;
    private Status status;
}
