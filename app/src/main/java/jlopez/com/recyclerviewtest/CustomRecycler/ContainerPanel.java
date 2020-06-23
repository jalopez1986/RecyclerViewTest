package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ContainerPanel extends ViewGroup {
    private int numberOfCard;

    public ContainerPanel(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("jorge2 widthMeasureSpec " + MeasureSpec.toString(widthMeasureSpec));
        System.out.println("jorge2 heightMeasureSpec " + MeasureSpec.toString(heightMeasureSpec));

        int width;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.UNSPECIFIED) {
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

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(width/(numberOfCard+ 0.1)), MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(height* 1), MeasureSpec.EXACTLY);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }


        View child0 = getChildAt(0);
        width = child0.getMeasuredWidth() * getChildCount();

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int x = 0;
        int y = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View view = getChildAt(index);
            view.layout(x, y, x + view.getMeasuredWidth(), y + (view.getMeasuredHeight()));
            x = x + view.getMeasuredWidth();
        }

    }

    public void setNumberOfCard(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }
}
