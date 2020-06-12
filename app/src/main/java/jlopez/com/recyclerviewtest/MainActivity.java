package jlopez.com.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jlopez.com.recyclerviewtest.test1.Test1Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest1 = findViewById(R.id.btnTest1);

        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTest1Activity();
            }
        });
    }

    private void goToTest1Activity() {
        Intent intent = new Intent(this, Test1Activity.class);
        startActivity(intent);
    }
}
