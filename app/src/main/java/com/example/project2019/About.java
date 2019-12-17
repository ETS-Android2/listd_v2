package com.example.project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

public class About extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(view -> finish());
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
                Intent intent = new Intent(About.this, MainActivity.class);
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_1:
                Intent intent1 = new Intent(About.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_2:
                Intent intent2 = new Intent(About.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.menu_item_3:
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
