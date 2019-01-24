package me.sneha.laborpredictor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ViewRecords extends AppCompatActivity {

    ListView lvRecords;
    ArrayAdapter<String> adp;
    EditText etSearchbar;
    SQLiteDatabase dbase;
    Cursor cursor;
    String[] str;



    dbhelper db;
    SharedPreferences share;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);




        etSearchbar=findViewById(R.id.etSearchbar);

        lvRecords=findViewById(R.id.lvRecords);
        dbhelper db=new dbhelper(this);
        dbase=db.getReadableDatabase();
        String q="select * from user";
        cursor=dbase.rawQuery(q,null);

        str=new String[cursor.getCount()];
        for(int j=0;j<cursor.getCount();j++){
            cursor.moveToNext();
            str[j]=" "+cursor.getString(0);
        }

//        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str);
        adp=new ArrayAdapter<>(this,R.layout.mylistitem ,str);
        lvRecords.setAdapter(adp);

        lvRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(ViewRecords.this,RecordDetail.class);
                i.putExtra("name",parent.getItemAtPosition(position).toString().trim());
                startActivity(i);
            }
        });

        etSearchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int c=0;
                Log.d("ViewRecords",s.toString());
//                String q="select * from user where name='"+s.toString()+"'";
                for(int i=0;i<str.length;i++){
                    if(str[i].toLowerCase().contains(s.toString().toLowerCase())){
                        c++;
                    }
                }

                String[] st=new String[c];
                c=0;
                for(int i=0;i<str.length;i++){
                    if(str[i].toLowerCase().contains(s.toString().toLowerCase())){
                        st[c]=str[i];
                        c++;
                    }
                }

                adp=new ArrayAdapter<>(ViewRecords.this,R.layout.mylistitem,st);
                lvRecords.setAdapter(adp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}
