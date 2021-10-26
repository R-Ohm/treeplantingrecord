package com.example.treeplantingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class logo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);



        new Thread(new Runnable() {
            public void run() {
                try  {
                    Thread.sleep(1500);
                }catch (InterruptedException e){}

                Intent intent = new Intent(logo.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();




    }
}