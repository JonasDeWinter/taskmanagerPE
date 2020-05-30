package ucll.be.taskmanagerpe.model.domain;


import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Proxy(lazy=false)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;
    @NotNull
    private LocalDateTime datum;
    @NotEmpty
    private String title;
    @NotEmpty
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

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public String getDescription() {
        return description;
    }
}
