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
        String table_create_query="create table User(name text,bpsys text,bpdiast text,fhr text,clength text,afluid text," +
                "fposition text,fpsub text,pposition text,ppsub text,result text)";
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


    String enterdata(String name,String bpsys,String bpdiast,String fhr,String clength,String afluid,String fposition,
                     String fpsub,String pposition,String ppsub,String res){
        try{
            SQLiteDatabase dbase=getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put("name",name);              //1
            values.put("bpsys",bpsys);
            values.put("bpdiast",bpdiast);
            values.put("fhr",fhr);
            values.put("clength",clength);            //5
            values.put("afluid",afluid);
            values.put("fposition",fposition);
            values.put("fpsub",fpsub);
            values.put("pposition",pposition);
            values.put("ppsub",ppsub);
            values.put("result",res);

            dbase.insert("user",null,values);

            return "success";

        }
        catch (Exception e){
            Log.d("log",""+e.getMessage());
            return "Failed"+e.getMessage();

        }
    }
}
