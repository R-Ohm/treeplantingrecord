package com.example.treeplantingrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatabaseInformation extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "information.db";
    private static final String TABLE_INFORMATION = "member";
    private static final String TABLE_INFORMATION2 = "product";

    public DatabaseInformation(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_INFORMATION +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT(1000),"+
                "password TEXT(1000),"+
                "status TEXT);");


        db.execSQL("CREATE TABLE " + TABLE_INFORMATION2 +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+
                "detail TEXT,"+
                "total TEXT(1000),"+
                "typedata TEXT(1000),"+
                "datauser TEXT,"+
                "codepro TEXT,"+
                "datestart TEXT,"+
                "img TEXT);");

        Log.d("CREATE TABLE","Create Table Successful.");
    }


    /// Check user register
    public String[] Checkusername(String tID) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_INFORMATION, new String[] { "*" },
                    "email=?",
                    new String[] { String.valueOf(tID)  }, null, null, null, null);


            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];

                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    //// Inser Username
    public long InsertData(String data1, String data2, String data3){
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase();

            ContentValues Val = new ContentValues();
            //Val.put("ID",strID);
            Val.put("email",data1);
            Val.put("password",data2);
            Val.put("status","M");

            long rows = db.insert(TABLE_INFORMATION,null,Val);

            db.close();
            return rows;
        }catch (Exception e){
            return -1;
        }
    }

    // Check Login
    public String[] Checkuser(String tID, String tID2) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_INFORMATION, new String[] { "*" },
                    "email=?",
                    new String[] { String.valueOf(tID) }, null, null, null, null);


            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];

                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Select Product  User
    public String[][]  SelectAllPro(String tID) {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df =  new SimpleDateFormat("yyyy/M/d");
            String formattedDate = df.format(c.getTime());

            String strSQL = "SELECT * FROM  "+TABLE_INFORMATION2+" where datauser = '"+tID+"' and typedata = '1' and datauser = '"+tID+"' order by ID desc " ;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }


    }

    //// Insert Product
    public long InserProduct(String data1, String data2, String data3, String data4, String data5, String data6, String data7, String datestart){
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase();

            ContentValues Val = new ContentValues();
            //Val.put("ID",strID);
            Val.put("name",data1);
            Val.put("detail",data2);
            Val.put("total",data3);
            Val.put("typedata",data4);
            Val.put("datauser",data5);
            Val.put("img",data6);

            Val.put("codepro",data7);
            Val.put("datestart",datestart);

            long rows = db.insert(TABLE_INFORMATION2,null,Val);

            db.close();
            return rows;
        }catch (Exception e){
            return -1;
        }
    }

    /// Check Product
    public String[] Checkusernameproduct(String tID) {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.query(TABLE_INFORMATION2, new String[] { "*" },
                    "name=?",
                    new String[] { String.valueOf(tID)  }, null, null, null, null);


            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];

                    arrData[0] = cursor.getString(0);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Select Bill
    public String[][]  SelectAllcartbillmember(String tID) {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df =  new SimpleDateFormat("yyyy/M/d");
            String formattedDate = df.format(c.getTime());

            String strSQL = "SELECT * FROM  "+TABLE_INFORMATION2+" where codepro = '"+tID+"' Order By id desc " ;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }


    }

    // Update Product
    public long UpdateProduct(String data1, String data2, String data3, String data4, String gencode) {
        // TODO Auto-generated method stub
        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("detail",data2);
            Val.put("img",data4);

            long rows = db.update(TABLE_INFORMATION2, Val, " codepro = '"+gencode+"'  ",null);

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }



    // Select Product  User
    public String[][]  SelectAllPro2(String tID, String search) {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df =  new SimpleDateFormat("yyyy/M/d");
            String formattedDate = df.format(c.getTime());

            String strSQL = "SELECT * FROM  "+TABLE_INFORMATION2+" where datauser = '"+tID+"' and typedata = '1' and name like '%"+search+"%' order by ID desc " ;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }


    }




    // Select Product  User
    public String[][]  SelectAllPro3(String tID) {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df =  new SimpleDateFormat("yyyy/M/d");
            String formattedDate = df.format(c.getTime());

            String strSQL = "SELECT * FROM  "+TABLE_INFORMATION2+" where codepro = '"+tID+"'  order by ID asc " ;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    int i2= 1;
                    do {
                        arrData[i][0] =  ""+(i2) ;
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        i++;
                        i2++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }


    }











    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);

        onCreate(db);
    }
}
