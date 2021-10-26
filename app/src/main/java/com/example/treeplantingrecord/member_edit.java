package com.example.treeplantingrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class member_edit extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    String name, name2, name3;
    String d1 = "";
    String d2 = "";
    String d3 = "";
    String d4 = "";
    String d5 = "";
    String d6 = "";
    String d7 = "";
    String d8 = "";
    String d9 = "";
    private TextView bm1, bm2, bm3, bm4, bm5, bm6;
    Spinner spin;
    private Button btnAdd, btnConfrim;

    private EditText no1, no2, no3, no4, no5, no6, no7, no8;
    String Str1,Str2,Str3,Str4,Str5,Str6,Str7,Str8;
    GridView gView1;
    ImageView buttonadd;
    ListView listViewMovies;

    TextView btn1, btn2;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView bug2_image ;
    private Bitmap bitmap;
    private Uri filePath;
    private TextView tvPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String image_camera = "0";
    String path_camera = "";
    String formattedDate2;
    String formattedDate4;
    TextView bynuploadimg;
    String nameimg = "";
    String path = "";
    private Button buttonsave, buttoncancel;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_edit);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");

        d1 = b.getString("D1");
        d2 = b.getString("D2");
        d3 = b.getString("D3");
        d4 = b.getString("D4");
        d5 = b.getString("D5");
        d6 = b.getString("D6");
        d7 = b.getString("D7");
        d8 = b.getString("D8");
        d9 = b.getString("D9");

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE }, 101);

        btn1 = (TextView) findViewById(R.id.btnupload);
        btn2 = (TextView) findViewById(R.id.btnupload2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        buttonsave = (Button) findViewById(R.id.save);
        buttonsave.setOnClickListener(this);

        no2 = (EditText) findViewById(R.id.e2);
        no3 = (EditText) findViewById(R.id.e3);
        no4 = (EditText) findViewById(R.id.e4);

        no5 = (EditText) findViewById(R.id.e5);

        no2.setText(d2);
        no3.setText(d3);
        no4.setText(d4);

        bug2_image = (ImageView)findViewById(R.id.imageView2);
        bug2_image.setOnClickListener(this);
        tvPath    = (TextView)findViewById(R.id.bug2_path);

        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        formattedDate2 = df2.format(c2.getTime());

        String  url = d9;
        if(url.equals("")){

        }else if(url.equals("Null")){

        }else{
            Picasso.with(member_edit.this).load(new File(url)).into(bug2_image);
        }


        final DatabaseInformation myDb = new DatabaseInformation(this);
        final String arrData3[][] = myDb.SelectAllcartbillmember(d7);
        int check_bill = 1;
        if(arrData3 == null){

        }else{
            for (int i=0; i<arrData3.length; i++) {
                check_bill += 1;
            }
        }
        no5.setText(""+check_bill);

        SimpleDateFormat df4 = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate4 = df4.format(c2.getTime());


        btnback = (ImageView) findViewById(R.id.imgbtnback);
        btnback.setOnClickListener(this);




    }


    public void onClick(View view) {
        if(view == btn1) {

            selectImage();

        }else   if(view == btn2){

            selectImage();


        }else   if(view == btnback) {

            Intent intent = new Intent(member_edit.this, member_list.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        }else  if(view == buttonsave) {

            String gencode =  d7;
            Str2 = no2.getText().toString().trim();
            Str3 = no3.getText().toString().trim();
            Str4 = no4.getText().toString().trim();

            String Check_pic = tvPath.getText().toString().trim();
            path = d9;

                if(image_camera.equals("1")){
                    path = path_camera;
                }else  if(image_camera.equals("2")){
                    path = getPath(filePath);
                }

                /// ถ้ามีให้อัพเดต ////
                final DatabaseInformation myDb = new DatabaseInformation(member_edit.this);
                long saveStatus = myDb.InserProduct(Str2, Str3, Str4, "2", name, path, gencode, formattedDate4);
                long saveStatus2 = myDb.UpdateProduct(Str2, Str3, Str4, path, gencode);

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_edit.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" อัพเดตเรียบร้อย ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        Intent intent = new Intent(member_edit.this, member_list.class);
                        intent.putExtra("A", name);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();




        }
    }

    public void onBackPressed() {

    }


    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE ) {
            if(data != null) {
                Bundle extras = data.getExtras();

                Bitmap imageBitmap = (Bitmap) extras.get("data");
                bug2_image.setImageBitmap(imageBitmap);

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);


                tvPath.setText("Path: " + getRealPathFromURI(tempUri));
                image_camera = "1";
                path_camera = getRealPathFromURI(tempUri);
            }
        }else if (requestCode == IMAGE_REQUEST_CODE )  {
            if(data != null) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    tvPath.setText("Path: ".concat(getPath(filePath)));
                    bug2_image.setImageBitmap(bitmap);
                    image_camera = "2";

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_edit.this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {


                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }

                } else if (options[item].equals("Choose from Gallery")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }




}