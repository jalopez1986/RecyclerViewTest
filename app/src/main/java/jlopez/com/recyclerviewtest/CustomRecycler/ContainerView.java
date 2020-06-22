package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import jlopez.com.recyclerviewtest.R;
import jlopez.com.recyclerviewtest.shared.Pet;

public class ContainerView extends ViewGroup  {
    Context context;
    RecyclerView recyclerView;

    public ContainerView(Context context) {
        super(context);
        this.context = context;
        initAttributes();
    }

    private void initAttributes() {
        LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        setBackgroundColor(Color.parseColor("#BADBEE"));

        recyclerView = createRecyclerView();

        addView(recyclerView);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        recyclerView.layout(0,0, recyclerView.getMeasuredWidth(), recyclerView.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("widthMeasureSpec " + MeasureSpec.toString(widthMeasureSpec));
        System.out.println("heightMeasureSpec " + MeasureSpec.toString(heightMeasureSpec));

        int width;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int)(widthSize * 0.5);
            if (widthSpecMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }


        int height;
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightSpecMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int)(heightSize * 0.8);
            if (heightSpecMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        int recyclerWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(width* 0.8), MeasureSpec.EXACTLY);
        int recyclerHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(height* 0.8), MeasureSpec.EXACTLY);

        recyclerView.measure(recyclerWidthMeasureSpec, recyclerHeightMeasureSpec);


        setMeasuredDimension(width, height);

    }


    private RecyclerView createRecyclerView() {
        recyclerView = new OneByOneRecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        AdapterData adapterData = new AdapterData(Pet.dummyData(), context);
        recyclerView.setAdapter(adapterData);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        return recyclerView;

    }
}
