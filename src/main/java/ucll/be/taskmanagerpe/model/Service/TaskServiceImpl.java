package ucll.be.taskmanagerpe.model.Service;


import org.springframework.stereotype.Service;
import ucll.be.taskmanagerpe.model.database.TaskDTO;
import ucll.be.taskmanagerpe.model.domain.SubTask;
import ucll.be.taskmanagerpe.model.domain.Task;
import ucll.be.taskmanagerpe.model.repository.TaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;
    private HashMap<Integer, ArrayList<SubTask>> subtasks;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        subtasks = new HashMap<>();
    }

    @Override
    public void addTask(TaskDTO task) {
        System.out.println(task.getId());
        subtasks.put(task.getId(),new ArrayList<>());

        Task task1 = new Task();
        task1.setId(task.getId());
        task1.setTitle(task.getTitle());
        task1.setDatum(task.getDatum());
        task1.setDescription(task.getDescription());

        taskRepository.save(task1);
    }

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(task -> {
            TaskDTO task1 = new TaskDTO();

            task1.setId(task.getId());
            task1.setTitle(task.getTitle());
            task1.setDatum(task.getDatum());
            task1.setDescription(task.getDescription());

            return task1;
        }).collect(Collectors.toList());
    }

    @Override
    public void edit(TaskDTO task) {
        Task task1 = taskRepository.getOne(task.getId()+1);
        task1.setTitle(task.getTitle());
        task1.setDatum(task.getDatum());
        task1.setDescription(task.getDescription());
        taskRepository.save(task1);
    }

    @Override
    public List<SubTask> getSubTasks(int id) {
        return subtasks.get(id);
    }
    //test
    @Override
    public TaskDTO getTaskById(int id) {
        Task task1 =  taskRepository.getOne(id );
        TaskDTO returntask = new TaskDTO();
        returntask.setId(task1.getId());
        returntask.setTitle(task1.getTitle());
        returntask.setDatum(task1.getDatum());
        returntask.setDescription(task1.getDescription());


        return returntask;
    }

    @Override
    public void addSubTask(int id, SubTask subTask) {
        subtasks.get(id).add(subTask);
    }
}
