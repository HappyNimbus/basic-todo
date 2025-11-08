package max_code.todolist.Service;

import lombok.RequiredArgsConstructor;
import max_code.todolist.DTO.ToDoRequest;
import max_code.todolist.DTO.ToDoResponse;
import max_code.todolist.Entities.Todo;
import max_code.todolist.Enums.Status;
import max_code.todolist.Repo.ToDoRepo;
import max_code.todolist.TodolistApplication;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

import java.security.Key;
import java.util.Map;

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

    public ToDoResponse partialUpdate(Long id, Map<String, Object> updates) {
        Todo todo = toDoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with ID: " + id));

        updates.forEach((fieldName, value) -> {
            try {
                Field field = Todo.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                if (field.getType().isEnum()) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Enum> enumType = (Class<? extends Enum>) field.getType();
                    Object enumValue = Enum.valueOf(enumType, value.toString());
                    field.set(todo, enumValue);
                } else {
                    field.set(todo, value);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Invalid field: " + fieldName);
            }
        });

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
