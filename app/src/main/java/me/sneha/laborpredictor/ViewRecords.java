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

    String[][] data={
            {"Sneha Chogale","158","normal","2.8","cephalic","posterior","12","Ceaserian"},
            {"Rupali Goje","150","normal","2.5","cephalic","anterior","9","Normal"},
            {"Priya Chile","138","normal","3.5","cephalic","anterior","8.18","Ceaserian"},
            {"Megha Waghchoure","161","normal","2.5","cephalic","posterior","9.2","Ceaserian"},
            {"Vaijanti Thonge","141","normal","3.38","shoulder","anterior","6.45","Ceaserian"},
            {"Namita Patil","132","normal","3","breech","anterior","10","Ceaserian"},
            {"Namita Patil","145","normal","3","shoulder","anterior","10","Ceaserian"},
            {"Sonali Vaje","139","normal","2.5","cephalic","posterior","8.8","Normal"},
            {"Swapnali Pashilkar","142","normal","3.3","cephalic","posterior","7.5","Normal"},
            {"Rupika Pandhere","150","normal","3.5","breech","anterior","8.6","Normal"},
            {"Archana Dagade","146","abnormal","3","cephalic","anterior","10","Normal"},
            {"Sonal Panchal","150","normal","3","cephalic","anterior","10","Normal"},
            {"Nandini Chogale","163","abnormal","3","cephalic","placenta pravia","11","Ceaserian"},
            {"Shubhngi Londe","136","normal","2.9","cephalic","anterior","6.5","Normal"},
            {"Poonam Patil","130","normal","3.1","cephalic","anterior","10","Normal"},
            {"Prachi Khedekar","146","normal","3.7","cephalic","posterior","7.2","Normal"},
            {"Supriya Gore","151","normal","3","cephalic","anterior","10","Normal"},
            {"Madhuri Patil","143","normal","4.1","cephalic","anterior","13","Ceaserian"},
            {"Priyanka Patil","152","normal","2","cephalic","posterior","13","Ceaserian"},
            {"Sonali Thorve","134","normal","3.3","cephalic","posterior","21","Ceaserian"},
            {"Sonali Thombare","145","normal","3","cephalic","anterior","10","Normal"},
            {"Shilpa Mahadik","134","normal","3","cephalic","anterior","8.9","Normal"},
            {"Sharmila Chile","136","normal","3","cephalic","anterior","8.9","Normal"},
            {"Tanuja Nate","142","normal","3.3","cephalic","posterior","6.2","Normal"},
            {"Anuradha Bhesare","134","normal","4.5","cephalic","posterior","6.9","Normal"},
            {"Priya Chogale","150","normal","3.3","cephalic","placenta bleeding","10","Ceaserian"},
            {"Sanika Chile","132","normal","3","cephalic","posterior","4.4","Ceaserian"},
            {"Sanika Chile","134","normal","3","breech","posterior","4.4","Ceaserian"},
            {"poonam Jalgaonkar","150","normal","1","cephalic","anterior","10","Ceaserian"},
            {"Swati Darge","151","normal","3.2","breech","anterior","6.5","Ceaserian"},
            {"Rupali Darge","162","normal","3","breech","posterior","10","Ceaserian"},
            {"Manjiri datir","135","normal","3.2","cephalic","posterior","8.9","Normal"},
            {"Pramodini Bhure","134","normal","4.5","cephalic","anterior","10","Normal"},
            {"Soniya Karpe","139","normal","3","breech","anterior","5.6","Ceaserian"},
            {"Vanita Khetam","152","normal","3","cephalic","posterior","10","Normal"},
            {"Anuprita Rane","149","normal","3.2","cephalic","posterior","6.9","Normal"},
            {"Sheetal Boritkar","143","normal","3","cephalic","anterior","10","Normal"},
            {"Vasudha Sahane","136","normal","3.9","cephalic","anterior","8.6","Normal"},
            {"Shilpa samarth","139","normal","4.3","cephalic","anterior","10","Normal"},
            {"Jyoshna Tatkare","146","normal","5.2","cephalic","anterior","9.2","Normal"},
            {"Tilekar Kavita","134","normal","3.9","breech","anterior","7.6","Ceaserian"},
            {"Shilpa Ohal","137","normal","3.2","cephalic","anterior","6","Ceaserian"},
            {"Hemangi Kondilkar","132","normal","3.2","cephalic","anterior","9.5","Normal"},
            {"Smita Nikam","148","normal","4.2","breech","posterior","17","Ceaserian"},
            {"Ujjwala Deshmukh","140","normal","3.5","shoulder","anterior","9.9","Ceaserian"},
            {"Rekha Sharma","151","normal","4.5","cephalic","anterior","10.2","Normal"},
            {"Usha Shivilkar","145","normal","3.2","cephalic","posterior","10.9","Normal"},
            {"Mangal Rikame","149","Abnormal","3.3","breech","posterior","9.4","Ceaserian"},
            {"Karuna Pawar","149","normal","3.5","cephalic","anterior","9.2","Normal"},
            {"Supriya Bandagale","148","normal","3.5","cephalic","posterior","9.4","Normal"},
            {"Tanvi kadam","149","normal","3.9","breech","anterior","8.6","Ceaserian"},
            {"sonam Birwadkar","138","normal","4.2","cephalic","anterior","8.9","Normal"},
            {"sonam Birwadkar","142","normal","4.8","cephalic","anterior","10.2","Normal"},
            {"Usha Mandal","134","normal","3.8","cephalic","posterior","8.8","Normal"},
            {"seema Bhise","142","normal","3.9","cephalic","anterior","11.2","Normal"},
            {"Khane Sonali","152","normal","4.2","cephalic","anterior","10.2","Normal"},
            {"Dipti bhat","134","normal","3.4","cephalic","anterior","8.9","Normal"},
            {"Swati kambali","149","normal","3.9","cephalic","posterior","8.5","Normal"},
            {"Supriya Labade","140","normal","3.9","cephalic","anterior","10.2","Normal"},
            {"Deepali Salve","135","normal","3.2","cephalic","anterior","8.4","Normal"},
            {"Sapana Kilanje ","152","normal","3.2","cephalic","anterior","8.9","Normal"},
            {"Sandhya Kasar","149","normal","4.2","cephalic","anterior","10.2","Normal"},
            {"Sandhya Kasar","140","normal","5.4","shoulder","posterior","10.2","Ceaserian"},
            {"Manisha Khandagale","140","normal","3.9","cephalic","posterior","5.3","Ceaserian"},
            {"Prema Deshmukh","135","normal","3.2","cephalic","anterior","5.2","Ceaserian"},
            {"Madhuri deshmukh","140","normal","3.9","cephalic","anterior","8.9","Normal"},
            {"Vaishali Chogale","130","normal","3.2","cephalic","placenta pravia","10.2","Ceaserian"},
            {"Priya Jadhav","151","normal","1","cephalic","posterior","10.9","Ceaserian"},
            {"Pranouti Todankar","140","normal","3.2","cephalic","posterior","8.5","Normal"},
            {"Sharmila khetam","135","normal","4.3","cephalic","anterior","10.4","Normal"}
    };

    dbhelper db;
    SharedPreferences share;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        share=getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);
       // Toast.makeText(this, ""+share.getString("StatusKey",""), Toast.LENGTH_SHORT).show();
        db=new dbhelper(this);
        dbase=db.getWritableDatabase();
        dbase.beginTransaction();
        try{
            ContentValues values=new ContentValues();
            for(int i=0;i<data.length;i++){

                values.put("name",data[i][0]);              //1
                values.put("bpsys",data[i][2]);
                values.put("bpdiast",data[i][2]);
                values.put("fhr",data[i][1]);
                values.put("clength",data[i][3]);            //5
                values.put("afluid",data[i][6]);
                values.put("fposition",data[i][4]);
                values.put("fpsub",data[i][4]);
                values.put("pposition",data[i][5]);
                values.put("ppsub",data[i][5]);
                values.put("result",data[i][7]);

                dbase.insert("user",null,values);
                //Log.d(tag,"write "+(i+1)+": "+strSearch[i][0]);
            }
            dbase.setTransactionSuccessful();
        }
        finally {
            dbase.endTransaction();
        }

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
