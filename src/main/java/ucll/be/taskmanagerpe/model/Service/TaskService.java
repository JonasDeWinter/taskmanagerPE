package ucll.be.taskmanagerpe.model.Service;

import ucll.be.taskmanagerpe.model.database.TaskDTO;
import ucll.be.taskmanagerpe.model.domain.SubTask;

import java.util.List;


public interface TaskService {
    void addTask(TaskDTO task);
    List<TaskDTO> getTasks();
    void edit(TaskDTO task);
    List<SubTask> getSubTasks(int id);
    TaskDTO getTaskById(int id);
    void addSubTask(int id, SubTask subTask);

}
