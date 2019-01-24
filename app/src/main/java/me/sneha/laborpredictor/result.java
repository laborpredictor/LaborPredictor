package me.sneha.laborpredictor;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {

    TextView tvResult1,tvResult2;
    Button btnYes,btnNo;
    String name,bpsys,bpdias,fhr,cl,af,fp,fpsub,pp,ppsub,res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        setTitle("");

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");      //1
        bpsys=extras.getString("bpsys");    //2
        bpdias=extras.getString("bpdias");  //3
        fhr=extras.getString("fhr");        //4
        cl=extras.getString("cl");          //5
        af=extras.getString("af");          //6
        fp=extras.getString("fp");          //7
        fpsub=extras.getString("fpsub");    //8
        pp=extras.getString("pp");          //9
        ppsub=extras.getString("ppsub");    //10
        res=extras.getString("res");        //11


        tvResult1=findViewById(R.id.tvResult1);
        tvResult2=findViewById(R.id.tvResult2);
        btnYes=findViewById(R.id.btnYes);
        btnNo=findViewById(R.id.btnNo);

//        tvResult1.setText("Delivery can be\n");
        if(res.equalsIgnoreCase("Ceaserian"))
            tvResult2.setTextColor(Color.RED);
        else
            tvResult2.setTextColor(Color.GREEN);
        tvResult2.setText(res);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper db=new dbhelper(result.this);
                String result=db.enterdata(name,bpsys,bpdias,fhr,cl,af,fp,fpsub,pp,ppsub,res);
                if(result.equalsIgnoreCase("success")) {
                    Toast.makeText(result.this, "Submitted !", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
