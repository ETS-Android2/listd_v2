package com.example.project2019.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.project2019.Controller.Controller;
import com.example.project2019.MainActivity;
import com.example.project2019.Model.Task;
import com.example.project2019.R;

import java.lang.reflect.Method;

public class AddTask extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {

    private EditText inputTitle, inputInfo;
    private Button addBtn, cancelBtn;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        inputTitle = findViewById(R.id.creationInputTitle);
        inputInfo = findViewById(R.id.creationInputInfo);
        addBtn = findViewById(R.id.addBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        controller = new Controller(this);

        addBtn.setOnClickListener(v -> {
            sendToController();
        });
        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }

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

    private void sendToController() {
        Task task = new Task();
        task.setTaskName(inputTitle.getText().toString());
        task.setTaskInfo(inputInfo.getText().toString());
        if (!checkField()) {
            Toast.makeText(this, "Make sure your fields are not empty", Toast.LENGTH_SHORT).show();
        } else {
            controller.postData(task);
        }
    }

    private boolean checkField() {
        boolean fields = true;
        if (inputTitle.length() == 0 || inputInfo.length() == 0) {
            fields = false;
        }
        return fields;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_0:
                Intent intent = new Intent(AddTask.this, MainActivity.class);
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_1:
                Intent intent1 = new Intent(AddTask.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_2:
                Intent intent2 = new Intent(AddTask.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.menu_item_3:
                Intent intent3 = new Intent(AddTask.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }
}
