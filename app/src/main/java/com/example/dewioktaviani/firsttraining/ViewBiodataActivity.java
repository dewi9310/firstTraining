package com.example.dewioktaviani.firsttraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewBiodataActivity extends AppCompatActivity {

    private TextView tvName, tvAddress, tvGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_biodata);
        tvName = (TextView)findViewById(R.id.tvName);
        tvAddress = (TextView)findViewById(R.id.tvAddress);
        tvGender = (TextView)findViewById(R.id.tvGender);

        People people = new People();
        if (getIntent().getExtras()!=null){
            tvName.setText(getIntent().getExtras().getString(people.Property_Name));
            tvAddress.setText(getIntent().getExtras().getString(people.Property_Address));
            tvGender.setText(getIntent().getExtras().getString(people.Property_Gender));
        }

    }
}
