package com.example.project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2019.Controller.Controller;
import com.example.project2019.Model.Task;

import java.lang.reflect.Method;

public class Modification extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {
    private TextView idTxt;
    EditText inputTitle, inputTaskInfo;
    private RadioGroup radioGroup;
    private RadioButton completedBtn, nonCompletedBtn;
    private Controller controller;
    private Button updateBtn, deleteBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);

        idTxt = findViewById(R.id.idTxt);
        inputTitle = findViewById(R.id.modifyTitleIInput);
        inputTaskInfo = findViewById(R.id.modifyTaskInfoInput);
        radioGroup = findViewById(R.id.radioGroup);
        completedBtn = findViewById(R.id.radioCompleted);
        nonCompletedBtn = findViewById(R.id.radioUnCompleted);
        cancelBtn = findViewById(R.id.cancelBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        controller = new Controller(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        Task task = (Task) bundle.getSerializable("TASK");
        idTxt.setText(task.getId());
        inputTitle.setText(task.getTaskName());
        inputTaskInfo.setText(task.getTaskInfo());

        Task updateTask = new Task();
        updateTask.setId(idTxt.getText().toString());

        if (task.getStatus().equalsIgnoreCase("Completed")) {
            completedBtn.setChecked(true);
        } else {
            nonCompletedBtn.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedBtn = group.findViewById(checkedId);
                boolean isChecked = checkedBtn.isChecked();
                if (isChecked) {
                    Task task = new Task();
                    task.setStatus(checkedBtn.getText().toString());
                    task.setId(idTxt.getText().toString());
                    task.setTaskName(inputTitle.getText().toString());
                    controller.statusChange(task);
                }
            }
        });
        updateBtn.setOnClickListener(v -> sendToController());
        deleteBtn.setOnClickListener(v -> sendToRemove());
        cancelBtn.setOnClickListener(v -> finish());
    }

    /*Sends information about new updated task to controller*/
    private void sendToController() {
        Task updateTask = new Task();
        updateTask.setId(idTxt.getText().toString());
        updateTask.setTaskName(inputTitle.getText().toString());
        updateTask.setTaskInfo(inputTaskInfo.getText().toString());
        if (!checkFields()) {
            Toast.makeText(this, "Make sure your fields are not empty", Toast.LENGTH_SHORT).show();
        } else {
            controller.updateData(updateTask);
        }
    }

    /*Removes data and sends to controller*/
    private void sendToRemove() {
        Task updateTask = new Task();
        updateTask.setId(idTxt.getText().toString());
        controller.removeData(updateTask);
        Modification.this.finish();
    }

    /*Radio boxes that checks whether a task i marked completed/uncompleted*/
    private boolean checkFields() {
        boolean fields = true;
        if (inputTitle.length() == 0 || inputTaskInfo.length() == 0) {
            fields = false;
        }
        return fields;
    }

    /*Menu*/
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu);
        popup.show();
        try {
            Method method = popup.getMenu().getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            method.setAccessible(true);
            method.invoke(popup.getMenu(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_1:
                Intent intent = new Intent(Modification.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_2:
                Intent intent1 = new Intent(Modification.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_3:
                Intent intent2 = new Intent(Modification.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            default:
                return false;
        }
    }
}
