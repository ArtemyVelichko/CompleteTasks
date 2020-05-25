package completetask.emptymindgames.completetasks;

import com.google.gson.annotations.SerializedName;

public class EditTask {

    @SerializedName("id")
    private int id;
    @SerializedName("active")
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public EditTask(int id, int active) {
        this.id = id;
        this.active = active;
    }


}
