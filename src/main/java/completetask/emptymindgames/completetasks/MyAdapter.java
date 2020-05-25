package completetask.emptymindgames.completetasks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import completetask.emptymindgames.completetasks.databinding.TaskBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Task> taskList;

    CheckBoxChanged mCallBack;
    public interface CheckBoxChanged{
       void changeCheckBox(int id, int active);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView idTextView;
        public CheckBox checkBox;




        public MyViewHolder(@NonNull View itemView, TextView nameTextView, TextView idTextView, CheckBox checkBox) {
            super(itemView);
            this.nameTextView = nameTextView;
            this.idTextView = idTextView;
            this.checkBox = checkBox;
        }
    }


    public MyAdapter(ArrayList<Task> tasks,CheckBoxChanged mCallBack) {
        this.taskList = tasks;
        this.mCallBack = mCallBack;

    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        TaskBinding taskBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.task, parent, false);
        MyViewHolder vh = new MyViewHolder(taskBinding.taskLayout, taskBinding.nameTask, taskBinding.id, taskBinding.checkbox);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(taskList.get(position).getId() == 4){
            Log.i("artemy","dcdc");
            taskList.get(position).getActive();
        }

        holder.idTextView.setText(Integer.toString(taskList.get(position).getId()));
        holder.nameTextView.setText(taskList.get(position).getName());
        if(taskList.get(position).getActive() == 1){
            holder.checkBox.setChecked(false);
        }else {
            holder.checkBox.setChecked(true);
        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int active = 1;
                if(isChecked){
                    active = 0;
                }
                mCallBack.changeCheckBox(taskList.get(position).getId(),active);
            }
        });

    }
    @Override
    public int getItemCount() {
        return taskList.size();
    }

}
