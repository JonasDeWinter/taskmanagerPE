package ucll.be.taskmanagerpe.model.database;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class TaskDTO {
    private int id;
    private LocalDateTime datum;
    private String title;
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }
    //test circle ci

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getEindDate() {
        return datum.toString();
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public String getDescription() {
        return description;
    }
}
