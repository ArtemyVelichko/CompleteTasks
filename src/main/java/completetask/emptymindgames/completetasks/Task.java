package completetask.emptymindgames.completetasks;



import java.util.List;


import com.google.gson.annotations.Expose;

public class Task{

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String time;

    @Expose
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
