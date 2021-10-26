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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonlogin;
    private TextView buttonregister;
    private EditText textphone, textpassword;
    private String phone;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonregister = (TextView) findViewById(R.id.register);
        buttonlogin  = (Button) findViewById(R.id.login);

        textphone = (EditText) findViewById(R.id.phone);
        textpassword = (EditText) findViewById(R.id.password);

        buttonregister.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);



    }

    public void onClick(View view) {
        if (view == buttonregister) {

            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);
            finish();


        } else if (view == buttonlogin) {

            username = textphone.getText().toString();
            String password = textpassword.getText().toString();


            if (username.equals("")|| password.equals("")) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage("กรุณากรอกข้อมูลให้ครบ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();


            }else {

                // Check User
                final DatabaseInformation myDb = new DatabaseInformation(this);
                final String arrData[] = myDb.Checkuser(username, password);

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                if(arrData != null)
                {
                    if(password.equals(arrData[2])){

                        String fullname = arrData[1];

                        if(arrData[3].equals("M")){
                            Intent intent = new Intent(MainActivity.this, member_home.class);
                            intent.putExtra("A", username);
                            startActivity(intent);
                            finish();
                        }


                    }else{
                        builder.setCancelable(false);
                        builder.setTitle("แจ้งเตือน");
                        builder.setMessage(" Password ผิด ");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }

                }else{

                    builder.setCancelable(false);
                    builder.setTitle("แจ้งเตือน");
                    builder.setMessage(" กรุณาตรวจสอบ username ");
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();

                }


            }

        }
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("  ยืนยัน การออกจากระบบ ");
        builder.setMessage("คุณจะออกจากระบบ จริงหรือไม่ ?");

        builder.setPositiveButton("ออก", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("ไม่ออก", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }




}