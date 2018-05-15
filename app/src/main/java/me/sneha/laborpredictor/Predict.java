package me.sneha.laborpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Predict extends AppCompatActivity {

    EditText etName,etAge,etBPSystol,etBPDiastol,etFW,etFHR,etCL;
    Spinner spFPType,spFPSubtype;
    ArrayAdapter<String> adpType,adpNormalSubtype,adpAbnormalSubtype;
    String[] strType={"Normal","Abnormal"};
    String[] strNormalSubtype={"Cephalic"};
    String[] strAbnormalSubtype={"Occipito","Shoulder","Breech","Brow"};

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
        spFPSubtype.setEnabled(false);

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
