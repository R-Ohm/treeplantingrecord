package com.example.treeplantingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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

public class member_list extends AppCompatActivity implements View.OnClickListener{

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
    ImageView btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");

        final DatabaseInformation myDb = new DatabaseInformation(this);

        no1 = (EditText) findViewById(R.id.e1);
        no1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String arrData2[][];
                String search = no1.getText().toString();
                arrData2 = myDb.SelectAllPro2(name, search);
                if(arrData2 == null){
                }else{
                    listViewMovies.setAdapter(new ImageAdapter(member_list.this, arrData2));
                }
            }
        });


        // get Data from SQLite
        listViewMovies = (ListView) findViewById(R.id.list1);
        String arrData2[][];
        arrData2 = myDb.SelectAllPro(name);

        bm1 = (TextView) findViewById(R.id.text0);
        if(arrData2 == null){

        }else{

            listViewMovies.setAdapter(new ImageAdapter(this, arrData2));
        }

        btnback = (ImageView) findViewById(R.id.imgbtnback);
        btnback.setOnClickListener(this);


    }

    public void onClick(View view) {
        if(view == btnback) {

            Intent intent = new Intent(member_list.this, member_addproduct.class);
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

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column_member2, null);
            }



            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            String url = "";

            if (lis[position][8].toString().equals("NULL")) {

            }else if (!lis[position][8].toString().equals("")) {

                url = lis[position][8].toString();
                Picasso.with(context).load(new File(url)).resize(490,350).into(imageView);

            }


            // ColPosition
            TextView txtPosition1 = (TextView) convertView.findViewById(R.id.name_order);
            txtPosition1.setText(Html.fromHtml(  lis[position][1].toString()   ));

            TextView btnedit = (TextView) convertView.findViewById(R.id.btnview);
            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String data1 =lis[position][0].toString();
                    String data2 =lis[position][1].toString();
                    String data3 =lis[position][2].toString();
                    String data4 =lis[position][3].toString();
                    String data5 =lis[position][4].toString();
                    String data6 =lis[position][5].toString();
                    String data7 =lis[position][6].toString();
                    String data8 =lis[position][7].toString();
                    String data9 =lis[position][8].toString();

                    Intent intent = new Intent(member_list.this, member_edit.class);
                    intent.putExtra("A", name);

                    intent.putExtra("D1", data1);
                    intent.putExtra("D2", data2);
                    intent.putExtra("D3", data3);
                    intent.putExtra("D4", data4);
                    intent.putExtra("D5", data5);
                    intent.putExtra("D6", data6);
                    intent.putExtra("D7", data7);
                    intent.putExtra("D8", data8);
                    intent.putExtra("D9", data9);
                    startActivity(intent);
                    finish();

                }
            });


            return convertView;


        }
    }


    public void onBackPressed() {

    }



}