package com.example.treeplantingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class member_addproduct extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    String name, name2, name3;
    String d1 = "";
    String d2 = "";
    private TextView bm1, bm2, bm3, bm4, bm5, bm6;
    Spinner spin;
    private Button btnAdd, btnConfrim;

    private EditText no1, no2, no3, no4, no5, no6, no7, no8;
    String Str1,Str2,Str3,Str4,Str5,Str6,Str7,nameimg,Str8;
    GridView gView1;
    ImageView buttonadd;
    ListView listViewMovies;

    TextView btn1, btn2;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_addproduct);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");

        btn1 = (TextView) findViewById(R.id.btn1);
        btn2 = (TextView) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);



        btnback = (ImageView) findViewById(R.id.imgbtnback);
        btnback.setOnClickListener(this);



    }

    public void onClick(View view) {
        if(view == btn1) {

            Intent intent = new Intent(member_addproduct.this, member_add.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        }else   if(view == btn2){

            Intent intent = new Intent(member_addproduct.this, member_list.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        }else   if(view == btnback) {

            Intent intent = new Intent(member_addproduct.this, member_home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();


        }
    }

    public void onBackPressed() {

    }

}