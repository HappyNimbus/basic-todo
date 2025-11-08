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

    public ToDoResponse updateTodo(Long id, ToDoRequest request){

        Todo todo = toDoRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Todo not found with ID: " + request.getId()));

        todo.setName(request.getName());
        todo.setDescription(request.getDescription());
        todo.setPriority(request.getPriority());
        todo.setStatus(request.getStatus());
        todo.setComments(request.getComments());

        Todo updatedTodo = toDoRepo.save(todo);

        return new ToDoResponse(
                updatedTodo.getName(),
                updatedTodo.getDescription(),
                updatedTodo.getStatus(),
                updatedTodo.getPriority(),
                updatedTodo.getComments()
        );
    }

    public void deleteTodo(Long id){
       if (!toDoRepo.existsById(id)){
           throw new RuntimeException("Todo not found with ID: " + id);
       }
       toDoRepo.deleteById(id);
    }
}
