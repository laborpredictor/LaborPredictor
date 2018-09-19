package me.sneha.laborpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class userguide extends AppCompatActivity {

    TextView tvGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userguide);

        tvGuide=findViewById(R.id.tvGuide);
    }
}
