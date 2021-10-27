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
So let's start by taking a look at my sketch. On the first page, it will be logged in. If you 
haven't created an account yet, press register. After that, it will go to the register page and 
enter your email and password. After logging in, it will go to the home page. Now it's still empty. 
Click on the plus button to go to the add page, and after that, we can insert photos from the library 
or open the camera. Next, enter the name of the tree. And the description of the tree followed by 
the number of plants. And the time of planting will go up automatically. After adding the 
information, there must be a repository that we can go in to trace what plants we used to plant. And 
has a history of how many trees have been planted. Alright, after embedding my brief, it's time for 
us to get into the actual app.

[carbcoll20-10-64.pdf](https://github.com/R-Ohm/treeplantingrecord/files/7423127/carbcoll20-10-64.pdf)


## Check user register
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

## Pictures
- Login and Register page

| Login | Responsive Web |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/139000566-0af0ee56-d88a-4e84-83fd-67d6a9263f97.jpg" length="400"> | <img src="https://user-images.githubusercontent.com/88524223/139000607-1cae1bd5-e563-4a58-98b1-d7a567d4bc0c.jpg" length="400">
