package max_code.todolist.Repo;

import max_code.todolist.Entities.Todo;
import max_code.todolist.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepo extends JpaRepository<Todo, Long> {

    Optional<Todo> findByStatus(Status status);
}
