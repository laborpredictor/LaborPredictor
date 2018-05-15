package me.sneha.laborpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Predict extends AppCompatActivity {

    EditText etName,etAge,etBPSystol,etBPDiastol,etFW,etFHR,etCL;
    Spinner spFPType,spFPSubtype;
    ArrayAdapter<String> adpType,adpNormalSubtype,adpAbnormalSubtype;
    String[] strType={"Normal","Abnormal"};
    String[] strNormalSubtype={"Cephalic"};
    String[] strAbnormalSubtype={"Occipito","Shoulder","Breech","Brow"};
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        etName=findViewById(R.id.etName);
        etAge=findViewById(R.id.etAge);
        etBPSystol=findViewById(R.id.etBPSystol);
        etBPDiastol=findViewById(R.id.etBPDiastol);
        etFW=findViewById(R.id.etFW);
        etFHR=findViewById(R.id.etFHR);
        etCL=findViewById(R.id.etCL);

        spFPType=findViewById(R.id.spFPType);
        spFPSubtype=findViewById(R.id.spFPSubtype);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout1=getLayoutInflater().inflate(R.layout.custom_toast,null);

                Toast tl=new Toast(Predict.this);
                tl.setDuration(Toast.LENGTH_SHORT);
                tl.setGravity(Gravity.CENTER,0,-200);
                tl.setView(layout1);
                tl.show();

                Toast.makeText(Predict.this, "Algorithm Implementation Baaki aahe Madam !", Toast.LENGTH_SHORT).show();

            }
        });

        adpType=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strType);
        adpNormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strNormalSubtype);
        adpAbnormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strAbnormalSubtype);

        spFPType.setAdapter(adpType);
        spFPType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:spFPSubtype.setAdapter(adpNormalSubtype);
                            break;
                    case 1:spFPSubtype.setAdapter(adpAbnormalSubtype);
                            break;
                }
                spFPSubtype.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
