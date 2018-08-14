package me.sneha.laborpredictor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbhelper extends SQLiteOpenHelper {
    private static final String db_name="user.db";
    private static final int db_version=1;


    public dbhelper(Context context){
        super(context,db_name,null,db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_create_query="create table User(name text,bps text,bpd text,fhr text,cl text)";
        db.execSQL(table_create_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if user exists.");
        onCreate(db);// create table again

    }

    String[][] showitems(){

        SQLiteDatabase dbase=getReadableDatabase();
        String query="select * from user";
        Cursor cursor=dbase.rawQuery(query,null);
        String[][] str=new String[cursor.getCount()][19];
        if(cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToNext();
//                str=str+" "+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"\n";
                str[i][0]=cursor.getString(0);
                str[i][1]=cursor.getString(1);
                str[i][2]=cursor.getString(2);
                str[i][3]=cursor.getString(3);
                str[i][4]=cursor.getString(4);
                str[i][5]=cursor.getString(5);
                str[i][6]=cursor.getString(6);
                str[i][7]=cursor.getString(7);
                str[i][8]=cursor.getString(8);
                str[i][9]=cursor.getString(9);
                str[i][10]=cursor.getString(10);
                str[i][11]=cursor.getString(11);
                str[i][12]=cursor.getString(12);
                str[i][13]=cursor.getString(13);
                str[i][14]=cursor.getString(14);
                str[i][15]=cursor.getString(15);
                str[i][16]=cursor.getString(16);
                str[i][17]=cursor.getString(17);
                str[i][18]=cursor.getString(18);

                //Log.d("dbhelper",str[i][0]+" "+str[i][1]+" "+str[i][2]+" \n");
            }
        }
        return str;
    }


    String enterdata(String villagename,String name,String age,String dob,String height,String weight,String gender,String maritalstatus,
                     String familymembers,String familyincome,String occupation,String chiefcomplaints,String kco,String pasthistory,
                     String habits,String probablediagnosis,String rx,String advice,String doctor){
        try{
            SQLiteDatabase dbase=getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put("villagename",villagename);              //1
            values.put("name",name);
            values.put("age",age);
            values.put("dob",dob);
            values.put("height",height);            //5
            values.put("weight",weight);
            values.put("gender",gender);
            values.put("maritalstatus",maritalstatus);
            values.put("familymembers",familymembers);
            values.put("familyincome",familyincome);          //10
            values.put("occupation",occupation);
            values.put("chiefcomplaints",chiefcomplaints);
            values.put("kco",kco);
            values.put("pasthistory",pasthistory);
            values.put("habits",habits);            //15
            values.put("probablediagnosis",probablediagnosis);
            values.put("rx",rx);
            values.put("advice",advice);
            values.put("doctor",doctor);            //19

            dbase.insert("user",null,values);

            return "success";

        }
        catch (Exception e){
            Log.d("log",""+e.getMessage());
            return "Failed"+e.getMessage();

        }
    }
}
