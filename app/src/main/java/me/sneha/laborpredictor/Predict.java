 package me.sneha.laborpredictor;

import android.content.Intent;
import android.database.Cursor;
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
    String[] strFPNormalSubtype={"Cephalic"};
    String[] strFPAbnormalSubtype={"Shoulder","Occipito","Breech","Brow"};
    String[] strPPNormalSubtype={"Posterior","Anterior"};
    String[] strPPAbnormalSubtype={"Placenta Abruption","Placenta Pravia"};
    Button btnSubmit;
    /*
    double nfhrn=1,nfhra=0,nbpn=0.9583333333,nbpa=0.0416666667,ncln=1,ncla=0,ncephalic=0.916667,ntransverse=0.0416666667,
            nbreech=0.041666667,nposterior=0.3333333333,nanterior=0.6666666667,npbleeding=0,nppravia=0,
            nafyn=0.833333333,nafya=0.166666667,nshoulder=0,noccipito=0,nbrow=0,nplacabr=0;
    double cfhrn=1,cfhra=0,cbpn=0.9375,cbpa=0.0625,ccln=0.875,ccla=0.125,ccephalic=0.625,ctransverse=0.0625,
            cbreech=0.3125,cposterior=0.4375,canterior=0.4375,cpbleeding=0.0625,cppravia=0.0625,
            cafyn=0.625,cafya=0.375,cshoulder=0,coccipito=0,cbrow=0,cplacabr=0;
            */
//    double evidenceN=0.6,evidenceC=0.4;
    double normal=1,ceasar=1;
    String res="";

    dbhelper db;
    SQLiteDatabase dbase;
    Cursor cursor;
    String[][] str;

    double s3=0,s2=0;
    double b2=0,c2=0,d2=0,e2=0,f2=0,g2=0,h2=0,i2=0,j2=0,k2=0,l2=0,m2=0,n2=0,o2=0,p2=0,q2=0,r2=0;
    double b3=0,c3=0,d3=0,e3=0,f3=0,g3=0,h3=0,i3=0,j3=0,k3=0,l3=0,m3=0,n3=0,o3=0,p3=0,q3=0,r3=0;

    double nfhrn=b2/s2,nfhra=c2/s2,nbpn=d2/s2,nbpa=e2/s2,ncln=f2/s2,ncla=g2/s2,ncephalic=h2/s2,noccipito=i2/s2,
            nbreech=j2/s2,nshoulder=k2/s2,nbrow=l2/s2,nposterior=m2/s2,nanterior=n2/s2,npbleeding=o2/s2,nppravia=p2/s2,
            nafyn=q2/s2,nafya=r2/s2;

    double cfhrn=b2/s2,cfhra=c2/s2,cbpn=d2/s2,cbpa=e2/s2,ccln=f2/s2,ccla=g2/s2,ccephalic=h2/s2,coccipito=i2/s2,
            cbreech=j2/s2,cshoulder=k2/s2,cbrow=l2/s2,cposterior=m2/s2,canterior=n2/s2,cpbleeding=o2/s2,cppravia=p2/s2,
            cafyn=q2/s2,cafya=r2/s2;

    double evidenceN=0,evidenceC=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        //calculate all values
        db=new dbhelper(this);
        dbase=db.getReadableDatabase();
        String q="select * from user";
        cursor=dbase.rawQuery(q,null);

        str=new String[cursor.getCount()][11];
        for(int j=0;j<cursor.getCount();j++){
            cursor.moveToNext();
            str[j][0]=cursor.getString(0);              //name
            str[j][1]=cursor.getString(1);  //bpsys
            str[j][2]=cursor.getString(2);//bpdias

            str[j][3]=cursor.getString(3); //fhr
            str[j][4]=cursor.getString(4); //cl
            str[j][5]=cursor.getString(5); //af
            str[j][6]=cursor.getString(6); //fp
            str[j][7]=cursor.getString(7); //fpsub
            str[j][8]=cursor.getString(8); //pp
            str[j][9]=cursor.getString(9); //ppsub

            str[j][10]=" "+cursor.getString(10); //result

            if(str[j][10].trim().equals("Ceaserian")) {
                s3++;
            }
            if (str[j][10].trim().equals("Normal")) {
                s2++;
            }

            //****dynamic calculation of all values required for calculations

            //fhrn and fhra
            if(Integer.parseInt(str[j][3])>99 && Integer.parseInt(str[j][3])<166 ) {
                if (str[j][10].trim().equals("Normal"))
                    b2++;
                else
                    b3++;
            }
            else{
                if (str[j][10].trim().equals("Normal"))
                    c2++;
                else
                    c3++;
            }

            //bpn bpa
            if(str[j][1].equalsIgnoreCase("normal")){
                if (str[j][10].trim().equals("Normal"))
                    d2++;
                else
                    d3++;
            }
            else
            if(str[j][1].equalsIgnoreCase("abnormal")){
                if (str[j][10].trim().equals("Normal"))
                    e2++;
                else
                    e3++;
            }
            else
            if(((Integer.parseInt(str[j][1])<131 && Integer.parseInt(str[j][1])>109 )
                    && (Integer.parseInt(str[j][2])<91 && Integer.parseInt(str[j][2])>69)))   {
                if (str[j][10].trim().equals("Normal"))
                    d2++;
                else
                    d3++;
            }
            else{
                if (str[j][10].trim().equals("Normal"))
                    e2++;
                else
                    e3++;
            }

            //cln and cla
            if(Double.parseDouble(str[j][4])>2.2) {
                if (str[j][10].trim().equals("Normal"))
                    f2++;
                else
                    f3++;
            }
            else{
                if (str[j][10].trim().equals("Normal"))
                    g2++;
                else
                    g3++;
            }


            //cephalic
            if(str[j][7].trim().equalsIgnoreCase("cephalic")) {
                if (str[j][10].trim().equals("Normal"))
                    h2++;
                else
                    h3++;
            }

            //occiput
            if(str[j][7].trim().equalsIgnoreCase("occipito")) {
                if (str[j][10].trim().equals("Normal"))
                    i2++;
                else
                    i3++;
            }


            //breech
            if(str[j][7].trim().equalsIgnoreCase("breech")) {
                if (str[j][10].trim().equals("Normal"))
                    j2++;
                else
                    j3++;
            }

            //sholder
            if(str[j][7].trim().equalsIgnoreCase("shoulder")) {
                if (str[j][10].trim().equals("Normal"))
                    k2++;
                else
                    k3++;
            }

            //brow
            if(str[j][7].trim().equalsIgnoreCase("brow")) {
                if (str[j][10].trim().equals("Normal"))
                    l2++;
                else
                    l3++;
            }

            //posterior
            if(str[j][9].trim().equalsIgnoreCase("posterior")) {
                if (str[j][10].trim().equals("Normal"))
                    m2++;
                else
                    m3++;
            }

            //anterior
            if(str[j][9].trim().equalsIgnoreCase("anterior")) {
                if (str[j][10].trim().equals("Normal"))
                    n2++;
                else
                    n3++;
            }

            //placenta abruption
            if(str[j][9].trim().equalsIgnoreCase("placenta bleeding")) {
                if (str[j][10].trim().equals("Normal"))
                    o2++;
                else
                    o3++;
            }

            //placenta pravia
            if(str[j][9].trim().equalsIgnoreCase("placenta pravia")) {
                if (str[j][10].trim().equals("Normal"))
                    p2++;
                else
                    p3++;
            }

            //afn and afab

            if(Double.parseDouble(str[j][5])<19 && Double.parseDouble(str[j][5])>7) { //changed from 6 to 7
                if (str[j][10].trim().equals("Normal"))
                    q2++;
                else
                    q3++;
            }
            else{
                if (str[j][10].trim().equals("Normal"))
                    r2++;
                else
                    r3++;
            }

            //****dynamic values calculation block ends

//            Log.d("*s",str[j][0]+" "+str[j][1]+" "+str[j][2]+" "+str[j][3]+" "+str[j][4]+" "+str[j][5]+" "+ str[j][6]+" "+str[j][7]+" "+str[j][8]+" "+str[j][9]+" "+str[j][10]);
//            Log.d("*s",ceas_count+" "+normal_count);
        }
        Log.d("*s","normal"+s2+" ceasar"+s3+"\nfhrn "+b2+" "+b3+" fhra "+c2+" "+c3+"\nbpn "+d2+" "+d3+" bpa "+e2+" "+e3
                +"\ncln "+f2+" "+f3+" cla "+g2+" "+g3+" afn "+q2+" "+q3+" afa "+r2+" "+r3+"\n"
                +" cep "+h2+" "+h3+" occiput "+i2+" "+i3+" shoulder "+k2+" "+k3+" breech "+j2+" "+j3
                +" brow "+l2+" "+l3+"\nposterior "+m2+" "+m3+" anterior "+n2+" "+n3+" placenta bleeding "+o2+" "+o3+", "
                +"placenta pravia "+p2+" "+p3);

        evidenceN=s2/str.length ;evidenceC=s3/str.length;

        nfhrn=b2/s2;nfhra=c2/s2;nbpn=d2/s2;nbpa=e2/s2;ncln=f2/s2;ncla=g2/s2;ncephalic=h2/s2;noccipito=i2/s2;
        nbreech=j2/s2;nshoulder=k2/s2;nbrow=l2/s2;nposterior=m2/s2;nanterior=n2/s2;npbleeding=o2/s2;nppravia=p2/s2;
        nafyn=q2/s2;nafya=r2/s2;

        cfhrn=b3/s3;cfhra=c3/s3;cbpn=d3/s3;cbpa=e3/s3;ccln=f3/s3;ccla=g3/s3;ccephalic=h3/s3;coccipito=i3/s3;
        cbreech=j3/s3;cshoulder=k3/s3;cbrow=l3/s3;cposterior=m3/s3;canterior=n3/s3;cpbleeding=o3/s3;cppravia=p3/s3;
        cafyn=q3/s3;cafya=r3/s3;

        Log.d("*s",nfhrn+", "+nfhra+", \n"+nbpn+", "+nbpa+", \n"+ncln+", "+ncla+", \n"+ncephalic+", "+noccipito+", "+
        nbreech+", "+nshoulder+", "+nbrow+", \n"+nposterior+", "+nanterior+", \n"+npbleeding+", "+nppravia+", \n"+nafyn+", "+nafya);

        Log.d("*s",cfhrn+", "+cfhra+", \n"+cbpn+", "+cbpa+", \n"+ccln+", "+ccla+", \n"+ccephalic+", "+coccipito+", "+
                cbreech+", "+cshoulder+", "+cbrow+", \n"+cposterior+", "+canterior+", \n"+cpbleeding+", "+cppravia+", \n"+cafyn+", "+cafya);
        /*double cfhrn=1,cfhra=0,cbpn=0.9375,cbpa=0.0625,ccln=0.875,ccla=0.125,ccephalic=0.625,coccipito=0,
                cbreech=0.3125,cshoulder=0,cbrow=0,cposterior=0.4375,canterior=0.4375,cpbleeding=0.0625,cppravia=0.0625,
                cafyn=0.625,cafya=0.375;
        */

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
                Log.d("laborp","clicked "+normal+" "+ceasar);
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



                    //for normal

                    if((Integer.parseInt(etBPSystol.getText().toString())<131 && Integer.parseInt(etBPSystol.getText().toString())>109 )
                            && (Integer.parseInt(etBPDiastol.getText().toString())<91 && Integer.parseInt(etBPDiastol.getText().toString())>69)){
                        normal=normal*evidenceN*nbpn;
                        Log.d("laborp","bp normal:"+normal);
                    }
                    else{
                        normal=normal*evidenceN*nbpa;
                        Log.d("laborp","bp abnormal:"+normal);
                    }


                    if(Integer.parseInt(etFHR.getText().toString())<166 && Integer.parseInt(etFHR.getText().toString())>99){
                        normal=normal*nfhrn;
                        Log.d("laborp","fhr normal:"+normal);
                    }
                    else{
                        normal=normal*nfhra;
                        Log.d("laborp","fhr abnormal:"+normal);
                    }


                    if(Double.parseDouble(etCL.getText().toString())>2.2 ){
                        normal=normal*ncln;
                        Log.d("laborp","cl normal:"+normal);
                    }
                    else{
                        normal=normal*ncla;
                        Log.d("laborp","cl abnormal:"+normal);
                    }


                    if(Double.parseDouble(etAF.getText().toString())<19 && Double.parseDouble(etAF.getText().toString())>7){//changed from 6 to 7
                        normal=normal*nafyn;
                        Log.d("laborp","afy normal:"+normal);
                    }
                    else{
                        normal=normal*nafya;
                        Log.d("laborp","afy abnormal:"+normal);
                    }


                    switch (spFPSubtype.getSelectedItem().toString().trim()){
                        case "Cephalic":normal=normal*ncephalic;
                            break;
//                        case "Transverse":normal=normal*ntransverse;
//                            break;
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
//                        case "Placental Abruption":normal=normal*nplacabr;
//                            break;
                        case "Placenta Bleeding":normal=normal*npbleeding;
                            break;
                        case "Placenta Pravia":normal=normal*nppravia;
                            break;
                    }
                    Log.d("laborp","pp normal:"+normal);

                    //for ceasar

                    if((Integer.parseInt(etBPSystol.getText().toString())<131 && Integer.parseInt(etBPSystol.getText().toString())>109 )
                            && (Integer.parseInt(etBPDiastol.getText().toString())<91 && Integer.parseInt(etBPDiastol.getText().toString())>69)){
                        ceasar=ceasar*evidenceC*cbpn;
                        Log.d("laborp","bp ceasar:"+ceasar);
                    }
                    else{
                        ceasar=ceasar*evidenceC*cbpa;
                        Log.d("laborp","bp abceasar:"+ceasar);
                    }


                    if(Integer.parseInt(etFHR.getText().toString())<166 && Integer.parseInt(etFHR.getText().toString())>99){
                        ceasar=ceasar*cfhrn;
                        Log.d("laborp","fhr ceasar:"+ceasar);
                    }
                    else{
                        ceasar=ceasar*cfhra;
                        Log.d("laborp","fhr abceasar:"+ceasar);
                    }


                    if(Double.parseDouble(etCL.getText().toString())>2.2 ){
                        ceasar=ceasar*ccln;
                        Log.d("laborp","cl ceasar:"+ceasar);
                    }
                    else{
                        ceasar=ceasar*ccla;
                        Log.d("laborp","cl abceasar:"+ceasar);
                    }


                    if(Double.parseDouble(etAF.getText().toString())<19 && Double.parseDouble(etAF.getText().toString())>6){
                        ceasar=ceasar*cafyn;
                        Log.d("laborp","afy ceasar:"+ceasar);
                    }
                    else{
                        ceasar=ceasar*cafya;
                        Log.d("laborp","afy abceasar:"+ceasar);
                    }


                    switch (spFPSubtype.getSelectedItem().toString().trim()){
                        case "Cephalic":ceasar=ceasar*ccephalic;
                            break;
//                        case "Transverse":ceasar=ceasar*ctransverse;
//                            break;
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
//                        case "Placental Abruption":ceasar=ceasar*cplacabr;
//                            break;
                        case "Placenta Bleeding":ceasar=ceasar*cpbleeding;
                            break;
                        case "Placenta Pravia":ceasar=ceasar*cppravia;
                            break;
                    }
                    Log.d("laborp","pp ceasar:"+ceasar);

                    if(normal>ceasar) {
//                        Toast.makeText(Predict.this, "Normal="+normal+"\nCeasar="+ceasar, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(Predict.this, "Delivery can be Normal !", Toast.LENGTH_LONG).show();
                        res="Normal";
                    }
                    else {
//                        Toast.makeText(Predict.this, "Delivery can be Ceaserian !", Toast.LENGTH_LONG).show();
                        res="Ceaserian";
                    }

                    Intent i=new Intent(Predict.this, me.sneha.laborpredictor.result.class);
                    i.putExtra("name",etName.getText().toString().trim());
                    i.putExtra("bpsys",etBPSystol.getText().toString().trim());
                    i.putExtra("bpdias",etBPDiastol.getText().toString().trim());
                    i.putExtra("fhr",etFHR.getText().toString().trim());
                    i.putExtra("cl",etCL.getText().toString().trim());
                    i.putExtra("af",etAF.getText().toString().trim());
                    i.putExtra("fp",spFPType.getSelectedItem().toString().trim());
                    i.putExtra("fpsub",spFPSubtype.getSelectedItem().toString().trim());
                    i.putExtra("pp",spPPType.getSelectedItem().toString().trim());
                    i.putExtra("ppsub",spPPSubtype.getSelectedItem().toString().trim());
                    i.putExtra("res",res);
                    startActivity(i);
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
