package me.sneha.laborpredictor;

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
        name=extras.getString("name");
        bpsys=extras.getString("bpsys");
        bpdias=extras.getString("bpdias");
        fhr=extras.getString("fhr");
        cl=extras.getString("cl");
        af=extras.getString("af");
        fp=extras.getString("fp");
        fpsub=extras.getString("fpsub");
        pp=extras.getString("pp");
        ppsub=extras.getString("ppsub");
        res=extras.getString("res");

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
