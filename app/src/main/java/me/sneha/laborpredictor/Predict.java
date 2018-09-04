package me.sneha.laborpredictor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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

    EditText etName,etBPSystol,etBPDiastol,etFW,etFHR,etCL,etAF;
    Spinner spFPType,spFPSubtype,spPPType,spPPSubtype;
    ArrayAdapter<String> adpType,adpFPNormalSubtype,adpFPAbnormalSubtype,adpPPNormalSubtype,adpPPAbnormalSubtype;
    String[] strType={"Normal","Abnormal"};
    String[] strFPNormalSubtype={"Cephalic","Vertex"};
    String[] strFPAbnormalSubtype={"Transverse","Occipito","Shoulder","Breech","Brow"};
    String[] strPPNormalSubtype={"Posterior","Anterior"};
    String[] strPPAbnormalSubtype={"Placental Abruption","Placenta Bleeding","Placenta Pravia"};
    Button btnSubmit;
    double nfhrn=1,nfhra=0,nbpn=1,nbpa=0,ncln=1,ncla=0,ncephalic=0.916667,ntransverse=0.0416666667,
            nbreech=0.041666667,nposterior=0.3333333333,nanterior=0.6666666667,npbleeding=0,nppravia=0,
            nafyn=0.2916666667,nafya=0.7083333333,nshoulder=0,noccipito=0,nbrow=0,nplacabr=0;
    double cfhrn=1,cfhra=0,cbpn=0.9375,cbpa=0.0625,ccln=0.875,ccla=0.125,ccephalic=0.375,cvertex=0.25,ctransverse=0.0625,
            cbreech=0.3125,cposterior=0.4375,canterior=0.4375,cpbleeding=0.0625,cppravia=0.0625,
            cafyn=0.4375,cafya=0.5625,cshoulder=0,coccipito=0,cbrow=0,cplacabr=0;
    double evidenceN=0.6,evidenceC=0.4;
    double normal=1,ceasar=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        etName=findViewById(R.id.etName);
       // etAge=findViewById(R.id.etAge);
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
                normal=1;ceasar=1;
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
                        && !etFHR.getText().toString().isEmpty() && !etCL.getText().toString().isEmpty() &&!etAF.getText().toString().isEmpty())
                {

//                    dbhelper db=new dbhelper(Predict.this);
//                    String result=db.enterdata(etName.getText().toString().trim(),etBPSystol.getText().toString().trim(),
//                            etBPDiastol.getText().toString().trim(),etFHR.getText().toString().trim(),etCL.getText().toString().trim(),
//                            etAF.getText().toString().trim(),spFPType.getSelectedItem().toString().trim(),spFPSubtype.getSelectedItem().toString().trim(),
//                            spPPType.getSelectedItem().toString().trim(),spPPSubtype.getSelectedItem().toString().trim());
//                    if(result.equalsIgnoreCase("success")) {
//                        Toast.makeText(Predict.this, "Submitted !", Toast.LENGTH_SHORT).show();
//                        onBackPressed();
//                    }

                    //for normal

                    if(Integer.parseInt(etBPSystol.getText().toString())<125 || Integer.parseInt(etBPDiastol.getText().toString())<90){
                        normal=normal*evidenceN*nbpn;
                    }
                    else{
                        normal=normal*evidenceN*nbpa;
                    }
                    Log.d("laborp","bp normal:"+normal);

                    if(Integer.parseInt(etFHR.getText().toString())<165 && Integer.parseInt(etFHR.getText().toString())>105){
                        normal=normal*nfhrn;
                    }
                    else{
                        normal=normal*nfhra;
                    }
                    Log.d("laborp","fhr normal:"+normal);

                    if(Double.parseDouble(etCL.getText().toString())>2.2 ){
                        normal=normal*ncln;
                    }
                    else{
                        normal=normal*ncla;
                    }
                    Log.d("laborp","cl normal:"+normal);

                    if(Double.parseDouble(etAF.getText().toString())<8 && Double.parseDouble(etAF.getText().toString())>5){
                        normal=normal*nafyn;
                    }
                    else{
                        normal=normal*nafya;
                    }
                    Log.d("laborp","afy normal:"+normal);

                    switch (spFPSubtype.getSelectedItem().toString().trim()){
                        case "Cephalic":normal=normal*ncephalic;
                            break;
                        case "Transverse":normal=normal*ntransverse;
                            break;
                        case "Occipito":normal=normal*noccipito;
                            break;
                        case "Shoulder":normal=normal*nshoulder;
                            break;
                        case "Breech":normal=normal*nbreech;
                            break;
                        case "Brow":normal=normal*nbrow;
                            break;
                    }
                    Log.d("laborp","fp normal:"+normal);

                    switch(spPPSubtype.getSelectedItem().toString().trim()){
                        case "Posterior":normal=normal*nposterior;
                            break;
                        case "Anterior":normal=normal*nanterior;
                            break;
                        case "Placental Abruption":normal=normal*nplacabr;
                            break;
                        case "Placenta Bleeding":normal=normal*npbleeding;
                            break;
                        case "Placenta Pravia":normal=normal*nppravia;
                            break;
                    }
                    Log.d("laborp","pp normal:"+normal);

                    //for ceasar

                    if(Integer.parseInt(etBPSystol.getText().toString())<125 || Integer.parseInt(etBPDiastol.getText().toString())<90){
                        ceasar=ceasar*evidenceC*cbpn;
                    }
                    else{
                        ceasar=ceasar*evidenceC*cbpa;
                    }
                    Log.d("laborp","bp ceasar:"+ceasar);

                    if(Integer.parseInt(etFHR.getText().toString())<165 && Integer.parseInt(etFHR.getText().toString())>105){
                        ceasar=ceasar*cfhrn;
                    }
                    else{
                        ceasar=ceasar*cfhra;
                    }
                    Log.d("laborp","fhr ceasar:"+ceasar);

                    if(Double.parseDouble(etCL.getText().toString())>2.2 ){
                        ceasar=ceasar*ccln;
                    }
                    else{
                        ceasar=ceasar*ccla;
                    }
                    Log.d("laborp","cl ceasar:"+ceasar);

                    if(Double.parseDouble(etAF.getText().toString())<8 && Double.parseDouble(etAF.getText().toString())>5){
                        ceasar=ceasar*cafyn;
                    }
                    else{
                        ceasar=ceasar*cafya;
                    }
                    Log.d("laborp","afy ceasar:"+ceasar);

                    switch (spFPSubtype.getSelectedItem().toString().trim()){
                        case "Cephalic":ceasar=ceasar*ccephalic;
                            break;
                        case "Transverse":ceasar=ceasar*ctransverse;
                            break;
                        case "Occipito":ceasar=ceasar*coccipito;
                            break;
                        case "Shoulder":ceasar=ceasar*cshoulder;
                            break;
                        case "Breech":ceasar=ceasar*cbreech;
                            break;
                        case "Brow":ceasar=ceasar*cbrow;
                            break;
                    }
                    Log.d("laborp","fp ceasar:"+ceasar);

                    switch(spPPSubtype.getSelectedItem().toString().trim()){
                        case "Posterior":ceasar=ceasar*cposterior;
                            break;
                        case "Anterior":ceasar=ceasar*canterior;
                            break;
                        case "Placental Abruption":ceasar=ceasar*cplacabr;
                            break;
                        case "Placenta Bleeding":ceasar=ceasar*cpbleeding;
                            break;
                        case "Placenta Pravia":ceasar=ceasar*cppravia;
                            break;
                    }
                    Log.d("laborp","pp ceasar:"+ceasar);

                    if(normal>ceasar)
//                        Toast.makeText(Predict.this, "Normal="+normal+"\nCeasar="+ceasar, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Predict.this, "Delivery can be Normal !", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Predict.this, "Delivery can be Ceaserian !", Toast.LENGTH_LONG).show();
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
