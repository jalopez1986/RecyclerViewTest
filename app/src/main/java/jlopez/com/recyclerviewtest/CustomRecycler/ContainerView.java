package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import jlopez.com.recyclerviewtest.shared.Pet;

public class ContainerView extends ViewGroup  {
    Context context;
    ContainerPanel containerPanel;
    HorizontalScrollView scrollView;

    public ContainerView(Context context) {
        super(context);
        this.context = context;
        initAttributes();
    }

    private void initAttributes() {
        LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        setBackgroundColor(Color.parseColor("#BADBEE"));
        containerPanel = new ContainerPanel(this.context);
        int numberOfCards = 1;
        containerPanel.setNumberOfCard(numberOfCards);
        createChildren();

        scrollView = new CustomHorizontalScroll(this.context);

        scrollView.addView(containerPanel);




        //addView(containerPanel);
        addView(scrollView);
    }

    private void createChildren() {
        int count = 0;
        for (Pet pet: Pet.dummyData()) {
            CardView cardView = new CardView(this.context);
            if (count == 0 || count %2 == 0) {
                cardView.setBackgroundColor(Color.BLUE);
            } else {
                cardView.setBackgroundColor(Color.GRAY);
            }
            containerPanel.addView(cardView.create(pet.getName(),String.valueOf(pet.getAge())));
            count = count + 1;
        }
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //containerPanel.layout(0,0, containerPanel.getMeasuredWidth(), containerPanel.getMeasuredHeight());
        scrollView.layout(0,0, scrollView.getMeasuredWidth(), scrollView.getMeasuredHeight());
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

        int recyclerWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(width* 1), MeasureSpec.EXACTLY);
        int recyclerHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(height* 1), MeasureSpec.EXACTLY);

        //containerPanel.measure(recyclerWidthMeasureSpec, recyclerHeightMeasureSpec);
        scrollView.measure(recyclerWidthMeasureSpec, recyclerHeightMeasureSpec);


        setMeasuredDimension(width, height);

    }

}
