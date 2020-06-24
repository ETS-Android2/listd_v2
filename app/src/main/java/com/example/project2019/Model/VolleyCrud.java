package com.example.project2019.Model;

public interface VolleyCrud {
    void postData(Task task);
    void updateData(Task task);
    void getData();
    void removeData(Task task);
    void statusChange(Task task);
}
