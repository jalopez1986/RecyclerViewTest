package jlopez.com.recyclerviewtest.CustomRecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import jlopez.com.recyclerviewtest.R;

public class CustomRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContainerView containerView = new ContainerView(this);

        setContentView(containerView);
    }
}
