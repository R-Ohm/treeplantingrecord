package com.example.treeplantingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register extends AppCompatActivity implements View.OnClickListener{

    private Button buttonlogin;
    private TextView buttoncancel;
    String Str0, Str1,Str2,Str3,Str4,Str5,Str6,Str7;

    private EditText no0, no1, no2, no3, no4, no5, no6, no7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        buttonlogin = (Button) findViewById(R.id.login);
        buttonlogin.setOnClickListener(this);
        buttoncancel = (TextView) findViewById(R.id.register);
        buttoncancel.setOnClickListener(this);

        no1 = (EditText) findViewById(R.id.data1);
        no2 = (EditText) findViewById(R.id.data2);
        no3 = (EditText) findViewById(R.id.data3);


    }

    public void onClick(View view) {
        if(view == buttonlogin) {
            submitForm();

        }else   if(view == buttoncancel) {
            Intent intent = new Intent(register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void submitForm() {

        Str1 = no1.getText().toString().trim();
        Str2 = no2.getText().toString().trim();
        Str3 = no3.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (Str1.equals("")|| Str2.equals("")| Str3.equals("")) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
            builder.setCancelable(false);
            builder.setTitle("แจ้งเตือน");
            builder.setMessage(" กรุณากรอกข้อมูลให้ครบ ");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

        }else if(!Str1.matches(emailPattern)){

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
            builder.setCancelable(false);
            builder.setTitle("แจ้งเตือน");
            builder.setMessage(" กรุณากรอก Email ให้ถูกต้อง ");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

        }else if (!Str2.equals(Str3)) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" Password และ Re-Password ไม่ตรงกัน ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
        }else{

            // Check User
            final DatabaseInformation myDb = new DatabaseInformation(this);
            String arrData[] = myDb.Checkusername(Str1);
            if(arrData != null)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" Email นี้ซ้ำในระบบ " + Str1);
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            }else{

                // Dialog
                final AlertDialog.Builder adb = new AlertDialog.Builder(this);
                AlertDialog ad = adb.create();

                // Save Data
                long saveStatus = myDb.InsertData( Str1, Str2, Str3);

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" สมัครสมาชิกเรียบร้อย ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        Intent intent = new Intent(register.this , MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();

            }



        }

    }



    public void onBackPressed() {



    }
}