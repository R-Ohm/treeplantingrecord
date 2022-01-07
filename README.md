# tree planting record

## cs50
>This was my final project for conclude the CS50 Introduction to Computer Sciense course.

>java, application, CS50

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


## Code

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

## Pictures
- Login and Register page

| Login | Responsive Web |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/139000566-0af0ee56-d88a-4e84-83fd-67d6a9263f97.jpg" width="400"> | <img src="https://user-images.githubusercontent.com/88524223/139000607-1cae1bd5-e563-4a58-98b1-d7a567d4bc0c.jpg" width="400">

- Homepage and Add/List data

| Homepage | Add/List Data |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/139002845-cf056d82-4043-422c-ad5b-572b1619f085.jpg" width="400"> | <img src="https://user-images.githubusercontent.com/88524223/139002874-d6c22a6e-b72c-47b2-a0a3-559ee5da2dae.jpg" width="400">

- Add and Add/List data

| Add | Home page after adding |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/139003542-890af34c-7176-48d5-a661-3e2dd591f1c1.jpg" width="400"> | <img src="https://user-images.githubusercontent.com/88524223/139003596-f4a3d93a-588a-45bf-b583-0bff0ea05a17.jpg" width="400">

- List data and history

| List data | history |
| :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/88524223/139003875-c1af4495-c239-42f8-a193-8f71263e4166.jpg" width="400"> | <img src="https://user-images.githubusercontent.com/88524223/139003922-4addca00-87b5-42a2-86f8-97a7d1e3ecd3.jpg" width="400">

## Demonstration on youtube
For the CS50 final project you have to make a video showning your project,
[My Final Project presentation](https://www.youtube.com/watch?v=xxVD9cRVDnY&t=14s)

## About CS50
CS50 is a openware course from Havard University and taught by David J. Malan

Introduction to the intellectual enterprises of computer science and the art of programming. This course teaches students how to think algorithmically and solve problems efficiently. Topics include abstraction, algorithms, data structures, encapsulation, resource management, security, and software engineering. Languages include C, Python, and SQL plus students’ choice of: HTML, CSS, and JavaScript (for web development).

Thank you for all CS50.

- Where I get CS50 course?
https://cs50.harvard.edu/x/2020/
