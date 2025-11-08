package max_code.todolist.Controller;

import lombok.RequiredArgsConstructor;
import max_code.todolist.DTO.ToDoRequest;
import max_code.todolist.DTO.ToDoResponse;
import max_code.todolist.Service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    public ResponseEntity<ToDoResponse> addTodo(@RequestBody ToDoRequest request){
        return ResponseEntity.ok(toDoService.addTodo(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoResponse> updateTodo(@PathVariable Long id, @RequestBody ToDoRequest request){
        return ResponseEntity.ok(toDoService.updateTodo(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ToDoResponse> deleteTodo(@PathVariable Long id){
        toDoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<ToDoResponse>> getAllTodos(){
        return ResponseEntity.ok(toDoService.getAllTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoResponse> getOneTodo(Long id){
        return ResponseEntity.ok(toDoService.getOneTodo(id));
    }

}
