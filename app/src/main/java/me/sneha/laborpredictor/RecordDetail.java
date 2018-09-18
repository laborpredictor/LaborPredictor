package me.sneha.laborpredictor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class RecordDetail extends AppCompatActivity {

    TextView name,bps,bpd,fhr,cl,af,fp,pp,tvResult,tvResTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        name=findViewById(R.id.name);
        bps=findViewById(R.id.bps);
        bpd=findViewById(R.id.bpd);
        fhr=findViewById(R.id.fhr);
        cl=findViewById(R.id.cl);
        af=findViewById(R.id.af);
        fp=findViewById(R.id.fp);
        pp=findViewById(R.id.pp);
        tvResult=findViewById(R.id.tvResult);
        tvResTitle=findViewById(R.id.tvResTitle);

        Bundle extras=getIntent().getExtras();
        String val=extras.getString("name");

        setTitle(val);

        dbhelper db=new dbhelper(this);
        SQLiteDatabase dbase=db.getReadableDatabase();

        String q="select * from user where name='"+val+"'";
        Cursor cursor=dbase.rawQuery(q,null);
        String[] str=new String[11 ];
        for(int j=0;j<cursor.getCount();j++){
            cursor.moveToNext();
            str[0]=cursor.getString(0);
            str[1]=cursor.getString(1);
            str[2]=cursor.getString(2);
            str[3]=cursor.getString(3);
            str[4]=cursor.getString(4);
            str[5]=cursor.getString(5);
            str[6]=cursor.getString(6);
            str[7]=cursor.getString(7);
            str[8]=cursor.getString(8);
            str[9]=cursor.getString(9);
            str[10]=cursor.getString(10);
        }


        name.setText(": "+str[0]);
        bps.setText(": "+str[1]);
        bpd.setText(": "+str[2]);
        fhr.setText(": "+str[3]);
        cl.setText(": "+str[4]);
        af.setText(": "+str[5]);
        fp.setText(": "+str[6]+" -> "+str[7]);
        pp.setText(": "+str[8]+" -> "+str[9]);
        if(str[10].equalsIgnoreCase("ceaserian")) {
            tvResult.setTextColor(Color.RED);
            tvResTitle.setTextColor(Color.RED);
        }
        else {
            tvResult.setTextColor(Color.rgb(0, 175, 0));
            tvResTitle.setTextColor(Color.rgb(0, 175, 0));
        }
        tvResult.setText(": "+str[10]);



    }


}
