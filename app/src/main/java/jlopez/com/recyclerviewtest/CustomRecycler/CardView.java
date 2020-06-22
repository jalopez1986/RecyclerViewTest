package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import jlopez.com.recyclerviewtest.R;

public class CardView extends LinearLayout {
    Context context;
    LinearLayout rootView;

    TextView textViewName;
    TextView textViewAge;
    public CardView(Context context) {
        super(context);
        this.context = context;
        this.rootView = new LinearLayout(context);
    }


    public View create(String name, String age) {
        System.out.println("jorge createContainerView");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setOrientation(LinearLayout.VERTICAL);

        textViewName = new TextView(context);
        textViewName.setText(name);

        textViewAge = new TextView(context);
        textViewAge.setText(age);

        ImageView img = new ImageView(context);
        img.setImageResource(R.drawable.imagen1);

        addView(textViewName);
        addView(textViewAge);
        addView(img);

        return this;



    }

    public void setInfo(String name, String age) {
        textViewName.setText(name);
        textViewAge.setText(age);
    }
}
