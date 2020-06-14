package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jlopez.com.recyclerviewtest.R;
import jlopez.com.recyclerviewtest.shared.Pet;

public class ContainerView  {
    ViewGroup view;
    Context context;

    public ContainerView(Context context) {
        this.context = context;
        this.view = new LinearLayout(context);
    }

    public View create() {
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setBackgroundColor(Color.parseColor("#BADBEE"));

        RecyclerView recyclerView = createRecyclerView();


        view.addView(recyclerView);

        return view;
    }

    private RecyclerView createRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        AdapterData adapterData = new AdapterData(Pet.dummyData(), context);
        recyclerView.setAdapter(adapterData);


        return recyclerView;

    }
}
