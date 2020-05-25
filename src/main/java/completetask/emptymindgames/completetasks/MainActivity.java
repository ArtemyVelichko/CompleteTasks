package completetask.emptymindgames.completetasks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import completetask.emptymindgames.completetasks.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    protected ActivityMainBinding binding;

    ArrayList<Task> tasksArrayList = new ArrayList<>();
    ArrayList<Integer> checkBoxesBefore = new ArrayList<>();
    ArrayList<Integer> checkBoxesAfter = new ArrayList<>();
    MyAdapter taskAdapter;
    RecyclerView recyclerView;
    TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonMenu.setOnClickListener((v) -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.mymenu);
            popupMenu.show();
        });
        recyclerView = binding.myRecyclerView;

        registerForContextMenu(recyclerView);

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.init();
        taskViewModel.getTaskRepository().observe(this, response -> {
            List<Task> tasks = response;
            tasksArrayList.addAll(tasks);
            taskAdapter.notifyDataSetChanged();
            setCheckBoxes();
        });


        setupRecyclerView();


    }

    private void setupRecyclerView() {
        if (taskAdapter == null) {
            taskAdapter = new MyAdapter(tasksArrayList, new MyAdapter.CheckBoxChanged() {
                @Override
                public void changeCheckBox(int id, int active) {
                    checkBoxesAfter.set(id - 1, active);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(taskAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            taskAdapter.notifyDataSetChanged();
        }
    }


    public void startAlertDialog() {
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View promptsView = li.inflate(R.layout.prompt, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        mDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView.findViewById(R.id.input_text);

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String name = userInput.getText().toString();
                                taskViewModel.addTask(name);
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = mDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            startAlertDialog();
        } else {
            editTask();
        }
        return false;
    }

    public void editTask() {

        int listSize = checkBoxesAfter.size();

        for (int i = 0; i < listSize; i++) {
            if (checkBoxesAfter != checkBoxesBefore) {
                taskViewModel.editTask(new EditTask(i + 1, checkBoxesAfter.get(i)));
            }
        }
    }

    public void setCheckBoxes() {
        for (Task t : tasksArrayList) {
            checkBoxesBefore.add(t.getActive());
            checkBoxesAfter.add(t.getActive());
        }
    }


}
