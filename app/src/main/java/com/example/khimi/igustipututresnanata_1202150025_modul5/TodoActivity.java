package com.example.khimi.igustipututresnanata_1202150025_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TodoActivity extends AppCompatActivity {
    //deklarasi objek
    private EditText mtoDo, mDescription, mPriority;
    private Database mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        setTitle("Add To Do");

        //inisialisasi objek  ambil data dari xml
        mtoDo = (EditText) findViewById(R.id.editText_Todo);
        mDescription = (EditText) findViewById(R.id.editText_Description);
        mPriority = (EditText) findViewById(R.id.editText_Priority);
        mdatabase = new Database(this);
    }

    @Override //method ketika kembali
    public void onBackPressed() {
        //intent ke main dari todoactivity
        Intent intent = new Intent(TodoActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //method untuk menambahkan kegiatan
    public void addTodo(View view) {
        // jika user menginput kegiatan
        if (mdatabase.inputdata(new AddData(mtoDo.getText().toString(), mDescription.getText().toString(), mPriority.getText().toString()))){
            //toast muncul
            Toast.makeText(this, "To Do List Ditambahkan !", Toast.LENGTH_SHORT).show();
            //intent menuju main
            startActivity(new Intent(TodoActivity.this, MainActivity.class));
            this.finish();
        }else { // jika tidak input oleh user
            Toast.makeText(this, "List tidak boleh tidak diisi", Toast.LENGTH_SHORT).show();
            mtoDo.setText(null);
            mDescription.setText(null);
            mPriority.setText(null);
        }
    }

}

