package com.example.project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project2019.Controller.Controller;

import java.lang.reflect.Method;

public class CompletedTasks extends AppCompatActivity implements android.view.View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private Controller controller;
    private ListView theListView;
    private Button deleteCompletedBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        theListView = findViewById(R.id.completedList);
        deleteCompletedBtn = findViewById(R.id.deleteCompletedBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        controller = new Controller(this, theListView);
        controller.getData();
        deleteCompletedBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(v -> finish());
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_0:
                Intent intent = new Intent(CompletedTasks.this, MainActivity.class);
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_1:
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_2:
                Intent intent2 = new Intent(CompletedTasks.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.menu_item_3:
                Intent intent3 = new Intent(CompletedTasks.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteCompletedBtn:
                controller.removeCompletedTasks();
                onResume();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.getData();
    }
}
