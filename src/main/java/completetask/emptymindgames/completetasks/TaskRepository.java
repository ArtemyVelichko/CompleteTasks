package completetask.emptymindgames.completetasks;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {

    private static TaskRepository taskRepository;

    public static TaskRepository getInstance() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }

    private TaskApi taskApi;

    public TaskRepository() {
        taskApi = RetrofitService.createService(TaskApi.class);
    }

    public MutableLiveData<List<Task>> getTasks() {
        final MutableLiveData<List<Task>> taskData = new MutableLiveData<>();
        taskApi.getTaskList().enqueue(new Callback<List<Task>>() {

            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                taskData.setValue(response.body());
                Log.i("Artemy", "coolget");
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                taskData.setValue(null);
            }
        });

        return taskData;
    }

    public void addTask(String name) {

        taskApi.addTask(name).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Artemy", "cool");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Artemy", "notcool");
            }
        });
    }

    public void editTask(EditTask editTask) {
        taskApi.editTask(editTask.getId(), editTask.getActive()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("Artemy", "coolpost");
            }

            @Override
            public void onFailure(Call<Object> all, Throwable t) {
                Log.i("Artemy", "notcool");
            }
        });
    }


}
