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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2019.Controller.Controller;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;

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

        addBtn.setOnClickListener(v -> openDialog());
        controller.getData();

        Calendar calender = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
        TextView textViewDate = findViewById(R.id.textView_currentDate);
        textViewDate.setText(currentDate);
    }

    /* Old menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_1:
                Intent intent = new Intent(MainActivity.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.menu_item_2:
                Intent intent1 = new Intent(MainActivity.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_3:
                Intent intent2 = new Intent(MainActivity.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

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
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_1:
                Intent intent1 = new Intent(MainActivity.this, CompletedTasks.class);
                Toast.makeText(this, "Completed tasks clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                return true;
            case R.id.menu_item_2:
                Intent intent2 = new Intent(MainActivity.this, Settings.class);
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.menu_item_3:
                Intent intent3 = new Intent(MainActivity.this, About.class);
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }

    private void openDialog() {
        CreationDialog cd = new CreationDialog(this);
        cd.show();
        cd.setOnDismissListener(dialog -> onRestart());
    }

    @Override
    public void onRestart() {
        super.onRestart();
        controller.getData();
    }
}
