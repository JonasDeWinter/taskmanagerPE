package ucll.be.taskmanagerpe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.taskmanagerpe.model.domain.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
