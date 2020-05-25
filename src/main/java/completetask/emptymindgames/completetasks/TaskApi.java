package completetask.emptymindgames.completetasks;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface TaskApi {
    @GET("func.php?load")
    Call<List<Task>> getTaskList();

    @FormUrlEncoded
    @POST("func.php?add")
    Call<String> addTask(@Field("name") String name);

    @FormUrlEncoded
    @POST("func.php?edit")
    Call<Object> editTask(@Field("id") int id, @Field("active") int active);
}
