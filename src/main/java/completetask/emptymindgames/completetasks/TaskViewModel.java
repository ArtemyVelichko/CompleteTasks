package completetask.emptymindgames.completetasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private MutableLiveData<List<Task>> mutableLiveData;
    private TaskRepository taskRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        taskRepository = TaskRepository.getInstance();
        mutableLiveData = taskRepository.getTasks();
    }

    public LiveData<List<Task>> getTaskRepository() {
        return mutableLiveData;
    }

    public void addTask(String name) {
        taskRepository.addTask(name);
    }

    public void editTask(EditTask editTask) {
        taskRepository.editTask(editTask);
    }


}
