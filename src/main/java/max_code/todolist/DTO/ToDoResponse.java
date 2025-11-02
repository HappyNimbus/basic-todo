package max_code.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import max_code.todolist.Enums.Priority;
import max_code.todolist.Enums.Status;

@Data
@AllArgsConstructor
public class ToDoResponse {

    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private String comments;

}

