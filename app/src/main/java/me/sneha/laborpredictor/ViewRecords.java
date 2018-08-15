package me.sneha.laborpredictor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewRecords extends AppCompatActivity {

    ListView lvRecords;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        lvRecords=findViewById(R.id.lvRecords);
        dbhelper db=new dbhelper(this);
        SQLiteDatabase dbase=db.getReadableDatabase();
        String q="select * from user";
        Cursor cursor=dbase.rawQuery(q,null);
        String[][] str=new String[cursor.getCount()][19];
        String [] str1=new String[cursor.getCount()];
        for(int j=0;j<cursor.getCount();j++){
            cursor.moveToNext();
            str1[j]=cursor.getString(0);
        }

        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str1);
        lvRecords.setAdapter(adp);


    }
}
