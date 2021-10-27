# tree planting record

## cs50
>This was my final project for conclude the CS50 Introduction to Computer Sciense course.

>CS, java, application, CS50

## Explaining the project 
Hello my name is Ittipaht Kijpaisansak. I'm from Bangkok, Thailand. And this is my Havard cs50 
final project. I got my inspiration from seeing my dad planting around our house every day. This 
is not my dad, but it gives the same meaning. So, I decided to create a mobile app called tree 
planting record. The primary purpose of this project is to enable people who facilitate planting 
trees to remember their things because the country where I live is very close to the farming 
profession because Thailand is a country in the tropics, which is suitable for growing very much.

## draft
[carbcoll20-10-64.pdf](https://github.com/R-Ohm/treeplantingrecord/files/7423127/carbcoll20-10-64.pdf)


### Check user register
```java
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
``` 

### Inser Username
``` java
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
``` 

Validations for username, password, etc:

| Login | Responsive Web |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/138989854-e71eb770-a17b-4b18-8c22-725723220def.jpg" width="400"> | <img src="Screenshots/responsive.gif" width = "300">

