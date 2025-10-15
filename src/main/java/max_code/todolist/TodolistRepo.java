package max_code.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepo extends JpaRepository<Todo, Long> {
}
