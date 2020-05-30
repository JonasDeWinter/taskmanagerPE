package ucll.be.taskmanagerpe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ucll.be.taskmanagerpe.model.Service.TaskService;
import ucll.be.taskmanagerpe.model.database.TaskDTO;
import ucll.be.taskmanagerpe.model.domain.SubTask;
import ucll.be.taskmanagerpe.model.domain.Task;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TaskmanagerController {
    private TaskService taken;
    private int id;
    @Autowired
    public TaskmanagerController(TaskService service){
        taken = service;
    }
    @GetMapping
    public String getIndex(Model model){
        return "index";
    }

    @GetMapping("tasks")
    public String getDemo(Model model){
        model.addAttribute("tasks",taken.getTasks());
        return "taskmanager";
    }

    @GetMapping("tasks/{id}")
    public String details(Model model, @PathVariable int id){
        this.id = id;
        if(id>taken.getTasks().size()){
            model.addAttribute("task",null);
        }else{
            model.addAttribute("task",taken.getTaskById(id));
            model.addAttribute("subtasks",taken.getSubTasks(id-1));
        }
        return "detail";
    }


    @GetMapping("tasks/new")
    public String addTaskPagina(Model model){
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("tasks/addTask")
    public String addTask(@ModelAttribute @Valid TaskDTO taak, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taken.addTask(taak);
        return "redirect:/tasks";
    }

    @GetMapping("tasks/edit/{id}")
    public String editTaskPagina(Model model,@PathVariable int id){
        this.id = id;
        if(id>=taken.getTasks().size()){
            model.addAttribute("task",new Task());
        }else{
            model.addAttribute("task",taken.getTaskById(id));
        }
        return "editTask";
    }

    @PostMapping("tasks/editTask")
    public String editTask(@ModelAttribute @Valid TaskDTO taak, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taken.edit(taak);

        return "redirect:/tasks/"+id;
    }

    @GetMapping("tasks/{id}/sub/create")
    public String addSubTask(Model model,@PathVariable int id){
        this.id = id;
        if(id>=taken.getTasks().size()){
            model.addAttribute("task",new Task());
        }else{
            model.addAttribute("task",taken.getTaskById(id));
        }
        return "addSubTask";
    }

    @PostMapping("tasks/addSubTask")
    public String addSubTask(@ModelAttribute @Valid SubTask taak, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addSubTask";
        }
        taken.addSubTask(id-1,taak);

        return "redirect:/tasks/" + id;
    }
}
