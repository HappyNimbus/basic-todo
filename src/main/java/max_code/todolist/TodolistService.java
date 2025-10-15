package max_code.todolist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodolistService {

    @Autowired
    private TodolistRepo todoRepo;

    public List<Todo> getAllTodos(){
        return todoRepo.findAll();
    }

    public Optional<Todo> getTodoById(long id){
        return todoRepo.findById(id);
    }

    public Todo createTodo(Todo todo){
        if(todo.getTitle() == null || todo.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("Title required");
        }
        return todoRepo.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo){
        return todoRepo.findById(id).map(todo ->{
            todo.setTitle(updatedTodo.getTitle());
            todo.setDesc(updatedTodo.getDesc());
            todo.setComplete(updatedTodo.isComplete());
            return todoRepo.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found" + id));
    }

    public void deletetodo(Long id){
        todoRepo.deleteById(id);
    }
}
