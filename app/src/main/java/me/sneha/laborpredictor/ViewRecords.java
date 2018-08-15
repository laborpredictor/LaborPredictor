package me.sneha.laborpredictor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        String[] str=new String[cursor.getCount()];
        for(int j=0;j<cursor.getCount();j++){
            cursor.moveToNext();
            str[j]=cursor.getString(0);
        }

        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str);
        lvRecords.setAdapter(adp);

        lvRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(ViewRecords.this,RecordDetail.class);
                i.putExtra("name",parent.getItemAtPosition(position).toString().trim());
                startActivity(i);
            }
        });


    }
}
