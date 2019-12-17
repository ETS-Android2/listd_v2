package com.example.project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.project2019.Controller.Controller;

import java.lang.reflect.Method;

public class Settings extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private EditText emailInput;
    private Button enableBtn, cancelBtn;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        controller = new Controller(this);
        emailInput = findViewById(R.id.emailinput);
        enableBtn = findViewById(R.id.enableEmailBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

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
                Intent intent = new Intent(Settings.this, MainActivity.class);
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_1:
                Intent intent1 = new Intent(Settings.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_2:
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_3:
                Intent intent3 = new Intent(Settings.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }
}
