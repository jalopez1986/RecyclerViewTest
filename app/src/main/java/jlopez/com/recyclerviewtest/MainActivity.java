package jlopez.com.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jlopez.com.recyclerviewtest.CustomRecycler.CustomRecyclerActivity;
import jlopez.com.recyclerviewtest.SimpleRecycler.SimpleRecyclerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSimple = findViewById(R.id.btnSimple);
        Button btnCustom = findViewById(R.id.btnCustom);


        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSimpleRecyclerActivity();
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCustomRecyclerActivity();
            }
        });
    }


    private void goToSimpleRecyclerActivity() {
        Intent intent = new Intent(this, SimpleRecyclerActivity.class);
        startActivity(intent);
    }


    private void goToCustomRecyclerActivity() {
        Intent intent = new Intent(this, CustomRecyclerActivity.class);
        startActivity(intent);
    }
}
