package max_code.todolist;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String desc;
    private boolean complete = false;

    public Todo(){}
    public Todo(String title, String desc){
        this.title = title;
        this.desc = desc;
    }

    //getter setter
    public boolean isComplete() {
        return complete;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


}
