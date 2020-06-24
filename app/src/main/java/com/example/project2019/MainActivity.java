package com.example.project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2019.Controller.Controller;
import com.example.project2019.Views.About;
import com.example.project2019.Views.AddTask;
import com.example.project2019.Views.CompletedTasks;
import com.example.project2019.Views.Settings;

import java.lang.reflect.Method;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {
    private Controller controller;
    private ListView listView;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);
        controller = new Controller(this, listView);
        addBtn = findViewById(R.id.addTaskBtn);

        addBtn.setOnClickListener((View v) -> {
            Intent addToTaskIntent = new Intent(MainActivity.this, AddTask.class);
            startActivity(addToTaskIntent);
        });

        controller.getData();

        Date date = new Date();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("EEEE, MMM d");
        String currentDate = df.format(date);
        TextView textViewDate = findViewById(R.id.textView_currentDate);
        textViewDate.setText(currentDate);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_2);
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
                Intent intent1 = new Intent(MainActivity.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_1:
                Intent intent2 = new Intent(MainActivity.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.menu_item_2:
                Intent intent3 = new Intent(MainActivity.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        controller.getData();
    }
}
