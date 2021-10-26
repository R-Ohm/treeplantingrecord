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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class member_add extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    String name, name2, name3;
    String d1 = "";
    String d2 = "";
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
        setContentView(R.layout.activity_member_add);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");

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

        bug2_image = (ImageView)findViewById(R.id.imageView2);
        bug2_image.setOnClickListener(this);
        tvPath    = (TextView)findViewById(R.id.bug2_path);

        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        formattedDate2 = df2.format(c2.getTime());

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

            Intent intent = new Intent(member_add.this, member_addproduct.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();


        }else  if(view == buttonsave) {
            Random rand = new Random();
            int abcd = rand.nextInt(999999);

            String gencode = "C-"+abcd+"-"+formattedDate2;
            Str2 = no2.getText().toString().trim();
            Str3 = no3.getText().toString().trim();
            Str4 = no4.getText().toString().trim();

            String Check_pic = tvPath.getText().toString().trim();
            if (Check_pic.equals("")) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_add.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" กรุณาอัพโหลดภาพ ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            }else{
                if(image_camera.equals("1")){
                    path = path_camera;
                }else{
                    path = getPath(filePath);
                }


                /// ถ้ามีให้อัพเดต ////
                final DatabaseInformation myDb = new DatabaseInformation(member_add.this);

                String arrData[] = myDb.Checkusernameproduct(Str2);
                if(arrData != null)
                {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_add.this);
                    builder.setCancelable(false);
                    builder.setTitle("แจ้งเตือน");
                    builder.setMessage(" name นี้ซ้ำในระบบ " + Str2);
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }else{
                long saveStatus = myDb.InserProduct(Str2, Str3, Str4, "1", name, path, gencode, formattedDate4);

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_add.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" เพิ่มรายการเรียบร้อย ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        Intent intent = new Intent(member_add.this, member_home.class);
                        intent.putExtra("A", name);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();

                }


            }
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

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(member_add.this);
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