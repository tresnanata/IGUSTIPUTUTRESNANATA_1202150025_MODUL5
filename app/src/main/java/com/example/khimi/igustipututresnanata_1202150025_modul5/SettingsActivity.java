package com.example.khimi.igustipututresnanata_1202150025_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    //deklarasi objek
    private TextView mColor;
    int mColorid;
    AlertDialog.Builder mAlert;
    SharedPreferences.Editor mSharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); //melakukan set judul
        mAlert = new AlertDialog.Builder(this);
        //inisialisasi sharedpreferences
        SharedPreferences sharedPrefences = getApplicationContext().getSharedPreferences("Preferences", 0);
        mSharedpref = sharedPrefences.edit();
        mColorid = sharedPrefences.getInt("Colourground", R.color.white);

        mColor = findViewById(R.id.shape_color); //inisialisasi objek warna
        mColor.setText(getShapeColor(mColorid)); // set warna
    }

    //action untuk tombol back
    @Override
    public void onBackPressed() {
        //intent dari settingActivity ke main activity
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent); // start intent
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed(); //menjalankan method onBackPresed
        }
        return true;
    }


    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void pilihWarna(View view) { //method untuk memilih warna
        mAlert.setTitle("Shape Color");//set judul
        //membuat view baru
        View Mview = getLayoutInflater().inflate(R.layout.activity_change_color, null);
        mAlert.setView(Mview);

        //akses radio grup yang diambil dari xml
        final RadioGroup radG = Mview.findViewById(R.id.radio_color);
        radG.check(getColorid(mColorid));

        //set action button OK pada dialog
        mAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get radio button
                int a = radG.getCheckedRadioButtonId();
                // kondisi jika memilih radio button sesuai dengan warna
                switch (a){
                    case R.id.red:
                        mColorid = R.color.red;
                        break;
                    case R.id.green:
                        mColorid = R.color.green;
                        break;
                    case R.id.blue:
                        mColorid = R.color.blue;
                        break;
                    case R.id.white:
                        mColorid = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                mColor.setText(getShapeColor(mColorid));
                //menyimpan shared preference
                mSharedpref.putInt("Colourground", mColorid);
                mSharedpref.commit();
            }
        });

        //set action button negative/cancel pada dialog
        mAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mAlert.create().show(); //membuat dan nampilkan alert
    }


}
