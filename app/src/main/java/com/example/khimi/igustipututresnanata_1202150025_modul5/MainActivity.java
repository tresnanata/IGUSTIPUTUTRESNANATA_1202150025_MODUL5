package com.example.khimi.igustipututresnanata_1202150025_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Database mDataBase;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<AddData> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set judul
        setTitle("Todo List");
        //akses recyclerview
        mRecyclerView = findViewById(R.id.recycler_view);
        list_data = new ArrayList<>(); //buat array baru
        mDataBase = new Database(this); //buat db baru
        mDataBase.readdata(list_data); //panggil method data_list

        //inisialisasi shared preference
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedPreferences.getInt("Colourground", R.color.white);

        mAdapter = new Adapter(this,list_data, color);
        //mengatur perubahan ukuran
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        mRecyclerView.setAdapter(mAdapter);

        //menjalankan method hapus geser
        delete();

    }
    //method untuk menghapus list dengan geser
    public void delete(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = mAdapter.getData(position);
                //jika item digeser ke kiri
                if(direction==ItemTouchHelper.LEFT){
                    //akan menghapus item yang dipilih dengan primary key todonya
                    if(mDataBase.removedata(current.getTodo())){
                        mAdapter.deleteData(position);
                        //buat snackbar dan pemberitahuan bahwa item sudah terhapus dengan durasi 3 sekon
                        Snackbar.make(findViewById(R.id.coordinatorId), "List Telah Dihapus", 3000).show();
                    }
                }
            }
        };
        //tentuin itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRecyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang
        int id = item.getItemId();
        //jika klik settings
        if (id==R.id.action_settings){
            //intent ke menu Settings
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    //method yang di panggil pada tombol + di klik
    public void tambahlist(View view) {
        Intent intent = new Intent(MainActivity.this, TodoActivity.class);
        startActivity(intent);
    }
}

