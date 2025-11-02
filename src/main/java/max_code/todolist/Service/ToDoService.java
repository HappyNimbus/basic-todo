package max_code.todolist.Service;

import lombok.RequiredArgsConstructor;
import max_code.todolist.DTO.ToDoRequest;
import max_code.todolist.DTO.ToDoResponse;
import max_code.todolist.Entities.Todo;
import max_code.todolist.Enums.Status;
import max_code.todolist.Repo.ToDoRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo toDoRepo;

    public ToDoResponse addTodo(ToDoRequest request){

        Todo todo = Todo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(Status.NEW)
                .build();

        Todo savedTodo = toDoRepo.save(todo);

        return new ToDoResponse(
                savedTodo.getName(),
                savedTodo.getDescription(),
                savedTodo.getStatus(),
                savedTodo.getPriority(),
                savedTodo.getComments()
        );
    }
}
