package com.example.khimi.igustipututresnanata_1202150025_modul5;

/**
 * Created by Khimi on 3/25/2018.
 */

public class AddData {
    //deklarasi variabel
    String todo, description, priority;

    //konstruktor
    public AddData(String todo, String description, String priority){
        this.todo=todo;
        this.description=description;
        this.priority=priority;
    }

    //setter dan getter untuk to do , description dan priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesccription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}


