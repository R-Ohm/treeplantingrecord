package com.example.treeplantingrecord;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class member_pro_list extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    String name, name2, name3, name4, name5;
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
    ImageView btnback;
    private ImageView bug2_image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_pro_list);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");
        name2 = b.getString("D1");
        name3 = b.getString("D2");
        name4 = b.getString("D3");
        name5 = b.getString("D4");

        final DatabaseInformation myDb = new DatabaseInformation(this);
        // get Data from SQLite
        listViewMovies = (ListView) findViewById(R.id.list1);
        String arrData2[][];
        arrData2 = myDb.SelectAllPro3(name2);
        if(arrData2 == null){

        }else{

            listViewMovies.setAdapter(new ImageAdapter(this, arrData2));
        }

        bug2_image = (ImageView)findViewById(R.id.imageView2);
        String  url = name3;
        if(url.equals("")){

        }else if(url.equals("Null")){

        }else{
            Picasso.with(this).load(new File(url)).into(bug2_image);
        }


        bm1 = (TextView) findViewById(R.id.no2);
        bm1.setText(name4);

        btnback = (ImageView) findViewById(R.id.imgbtnback);
        btnback.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view == btnback) {

            Intent intent = new Intent(member_pro_list.this, member_home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();
        }
    }

    public class ImageAdapter extends BaseAdapter
    {
        private Context context;
        private String[][] lis;

        public ImageAdapter(Context c, String[][] li)
        {
            // TODO Auto-generated method stub
            context = c;
            lis = li;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return lis.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column_member3, null);
            }


            // ColPosition
            TextView txtPosition1 = (TextView) convertView.findViewById(R.id.txt1);
            TextView txtPosition2 = (TextView) convertView.findViewById(R.id.txt2);
            TextView txtPosition3 = (TextView) convertView.findViewById(R.id.txt3);
            TextView txtPosition4 = (TextView) convertView.findViewById(R.id.txt4);

            String dayDifference = "";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-M-u");

            String startDate = name5;
            String endDate = lis[position][7].toString();

            LocalDate startDateValue = LocalDate.parse(startDate, dateFormatter);
            LocalDate endDateValue = LocalDate.parse(endDate, dateFormatter);
            long days = ChronoUnit.DAYS.between(startDateValue, endDateValue) + 1;


            txtPosition1.setText(Html.fromHtml(  lis[position][0].toString() ));
            txtPosition2.setText(Html.fromHtml(  dateThai(lis[position][7].toString()) ));
            txtPosition3.setText(Html.fromHtml(  ""+days ));
            txtPosition4.setText(Html.fromHtml(  lis[position][3].toString() ));

            return convertView;


        }
    }


    public void onBackPressed() {

    }


    public static String dateThai(String strDate)
    {
        String Months[] = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"};

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        int year=0,month=0,day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return String.format("%s %s %s", day,Months[month],year);
    }
}