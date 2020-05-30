package ucll.be.taskmanagerpe.model.domain;


public class SubTask {
    private String title;
    private String description;
    public SubTask(){

    }
    public SubTask(String title, String  description){
        setDescription(description);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
