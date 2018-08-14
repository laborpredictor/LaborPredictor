package me.sneha.laborpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewRecords extends AppCompatActivity {

    ListView lvRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        lvRecords=findViewById(R.id.lvRecords);

    }
}
