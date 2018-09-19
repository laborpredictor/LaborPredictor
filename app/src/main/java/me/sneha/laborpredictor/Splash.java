package me.sneha.laborpredictor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //pd.setMessage("Starting...");
        //pd.show();

        new CountDownTimer(2000,500){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("tick",""+millisUntilFinished/500);
                if(((int)millisUntilFinished/1000)==0){
                    //pd.show();
                }

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(Splash.this,MainActivity.class));
            }
        }.start();
    }
}
