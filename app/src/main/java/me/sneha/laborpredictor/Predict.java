package me.sneha.laborpredictor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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

    EditText etName,etAge,etBPSystol,etBPDiastol,etFW,etFHR,etCL,etAF;
    Spinner spFPType,spFPSubtype,spPPType,spPPSubtype;
    ArrayAdapter<String> adpType,adpFPNormalSubtype,adpFPAbnormalSubtype,adpPPNormalSubtype,adpPPAbnormalSubtype;
    String[] strType={"Normal","Abnormal"};
    String[] strFPNormalSubtype={"Cephalic"};
    String[] strFPAbnormalSubtype={"Occipito","Shoulder","Breech","Brow"};
    String[] strPPNormalSubtype={"Posterior","Anterior"};
    String[] strPPAbnormalSubtype={"Low Lying","Placental Abruption"};
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
        etAF=findViewById(R.id.etAF);

        spFPType=findViewById(R.id.spFPType);
        spFPSubtype=findViewById(R.id.spFPSubtype);
        spPPType=findViewById(R.id.spPPType);
        spPPSubtype=findViewById(R.id.spPPSubtype);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*View layout1=getLayoutInflater().inflate(R.layout.custom_toast,null);

                Toast tl=new Toast(Predict.this);
                tl.setDuration(Toast.LENGTH_SHORT);
                tl.setGravity(Gravity.CENTER,0,-200);
                tl.setView(layout1);
                tl.show();
                Toast.makeText(Predict.this, "Algorithm Implementation Baaki aahe Madam !", Toast.LENGTH_SHORT).show();
                */
                if(etName.getText().toString().isEmpty()){
                    etName.setError("Enter Name");
                }
                if(etBPSystol.getText().toString().isEmpty()){
                    etBPSystol.setError("Enter Systol Value");
                }
                if(etBPDiastol.getText().toString().isEmpty()){
                    etBPDiastol.setError("Enter Diastol Value");
                }
                if(etFHR.getText().toString().isEmpty()){
                    etFHR.setError("Enter Fetus Heart Rate");
                }
                if(etCL.getText().toString().isEmpty()){
                    etCL.setError("Enter Cervix Length");
                }
                if(etAF.getText().toString().isEmpty()){
                    etAF.setError("Enter Amniotic Fluid value");
                }

                if(!etName.getText().toString().isEmpty() && !etBPSystol.getText().toString().isEmpty() && !etBPDiastol.getText().toString().isEmpty()
                        && !etFHR.getText().toString().isEmpty() && !etCL.getText().toString().isEmpty() &&!etAF.getText().toString().isEmpty()){

                    dbhelper db=new dbhelper(Predict.this);
                    String result=db.enterdata(etName.getText().toString().trim(),etBPSystol.getText().toString().trim(),
                            etBPDiastol.getText().toString().trim(),etFHR.getText().toString().trim(),etCL.getText().toString().trim(),
                            etAF.getText().toString().trim(),spFPType.getSelectedItem().toString().trim(),spFPSubtype.getSelectedItem().toString().trim(),
                            spPPType.getSelectedItem().toString().trim(),spPPSubtype.getSelectedItem().toString().trim());
                    if(result.equalsIgnoreCase("success")) {
                        Toast.makeText(Predict.this, "Submitted !", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        adpType=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strType);
        adpFPNormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strFPNormalSubtype);
        adpFPAbnormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strFPAbnormalSubtype);
        adpPPNormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strPPNormalSubtype);
        adpPPAbnormalSubtype=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strPPAbnormalSubtype);

        spFPType.setAdapter(adpType);
        spPPType.setAdapter(adpType);

        spFPType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:spFPSubtype.setAdapter(adpFPNormalSubtype);
                            break;
                    case 1:spFPSubtype.setAdapter(adpFPAbnormalSubtype);
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPPType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:spPPSubtype.setAdapter(adpPPNormalSubtype);
                        break;
                    case 1:spPPSubtype.setAdapter(adpPPAbnormalSubtype);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
