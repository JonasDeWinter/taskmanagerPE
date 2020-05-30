package ucll.be.taskmanagerpe;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.taskmanagerpe.model.Service.TaskServiceImpl;
import ucll.be.taskmanagerpe.model.database.TaskDTO;

import ucll.be.taskmanagerpe.model.domain.SubTask;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TaskServiceTests {

    @Autowired
    private TaskServiceImpl service;
    private LocalDateTime nu = LocalDateTime.of(2020,05,29,10,20,30);

    @Test
    @Order(1)
    public void testAddTask(){
        TaskDTO dto= new TaskDTO();
        dto.setDatum(nu);
        dto.setDescription("nieuwe taak");
        dto.setTitle("titel");

        service.addTask(dto);

        assertNotNull(service.getTasks().get(0));
        assertEquals(nu, service.getTasks().get(0).getDatum());
        assertEquals("titel", service.getTasks().get(0).getTitle());
        assertEquals("nieuwe taak", service.getTasks().get(0).getDescription());
        assertEquals(1,service.getTasks().size());
    }

    @Test
    @Order(2)
    public void testGetTasks(){
        System.out.println(service.getTasks());
        //te testen
        List<TaskDTO> tasks = service.getTasks();

        //actual tests
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }



    @Test
    @Order(3)
    public void testEditTask(){
        TaskDTO dto= new TaskDTO();
        dto.setId(service.getTasks().size()-1);
        dto.setDatum(nu);
        dto.setDescription("oude taak");
        dto.setTitle("titel");

        service.edit(dto);

        assertEquals(nu, service.getTasks().get(0).getDatum());
        assertEquals("titel", service.getTasks().get(0).getTitle());
        assertEquals("oude taak", service.getTasks().get(0).getDescription());
        assertEquals(1,service.getTasks().size());
    }

    @Test
    @Order(4)
    public void TestaddSubTaskandGetSubtasks(){
        SubTask sub = new SubTask();
        sub.setTitle("subtaskt");
        sub.setDescription("Description");
        service.addSubTask(0, sub);

        List<SubTask> subTasks = service.getSubTasks(0);

        assertNotNull(subTasks.get(0));
        assertFalse(subTasks.isEmpty());
        assertEquals(1,subTasks.size());
        assertEquals("subtaskt",subTasks.get(0).getTitle());
        assertEquals("Description",subTasks.get(0).getDescription());
    }

    @Test
    @Order(5)
    public void testGetTaskById(){
        TaskDTO testvariabele = service.getTaskById(1);

        assertEquals(service.getTasks().get(0).getDatum(), testvariabele.getDatum());
        assertEquals(service.getTasks().get(0).getTitle(), testvariabele.getTitle());
        assertEquals(service.getTasks().get(0).getDescription(), testvariabele.getDescription());
    }


}
