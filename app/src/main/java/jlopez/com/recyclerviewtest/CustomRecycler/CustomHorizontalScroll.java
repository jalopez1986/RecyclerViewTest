package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomHorizontalScroll extends HorizontalScrollView {


    boolean isFling = false;
    boolean showOnlyOneCard = true;
    int currentPosition;
    public CustomHorizontalScroll(Context context) {
        super(context);
        currentPosition = 0;
        setHorizontalScrollBarEnabled(false);



    }




    public interface OnEndScrollListener {
        public void onEndScroll();
    }

    private boolean mIsFling;
    private OnEndScrollListener mOnEndScrollListener;

    @Override
    public void fling(int velocityX) {
        System.out.println("Jorgecustom velocityX: " + velocityX);
        showOnlyOneCard = ((ContainerPanel)getChildAt(0)).getNumberOfCard() == 1;


        if (!showOnlyOneCard) { super.fling(velocityX); return; }

        int screenWidth = this.getMeasuredWidth();

        if (velocityX > 1) {
            currentPosition = currentPosition + 1;
        } else {
            if (currentPosition == 0) { return; }

            currentPosition = currentPosition - 1;
        }

        smoothScrollTo(screenWidth * currentPosition, 0);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        System.out.println("Jorgecustom onScrollChanged");
        showOnlyOneCard = ((ContainerPanel)getChildAt(0)).getNumberOfCard() == 1;


        super.onScrollChanged(x, y, oldX, oldY);

        /*if (!showOnlyOneCard) { super.onScrollChanged(x, y, oldX, oldY); return; }

        int childSize =   this.getMeasuredWidth() / ((ContainerPanel)getChildAt(0)).getNumberOfCard();

        int currentPos = Math.round(oldX / childSize) + 1;


        int screenWidth = this.getMeasuredWidth();
        smoothScrollTo(screenWidth * currentPosition, 0);
        */

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showOnlyOneCard = ((ContainerPanel)getChildAt(0)).getNumberOfCard() == 1;
        if(showOnlyOneCard && event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL ){

            System.out.println("Jorgecustom onTouchEvent");

            ContainerPanel containerPanel = ((ContainerPanel)getChildAt(0));

            int scrollX = getScrollX();
            int featureWidth = containerPanel.getChildAt(0).getMeasuredWidth();
            int mActiveFeature = ((scrollX + (featureWidth/2))/featureWidth);
            int scrollTo = mActiveFeature*featureWidth;
            smoothScrollTo(scrollTo, 0);

            return true;


        }

        return super.onTouchEvent(event);
    }

    public OnEndScrollListener getOnEndScrollListener() {
        return mOnEndScrollListener;
    }

    public void setOnEndScrollListener(OnEndScrollListener mOnEndScrollListener) {
        this.mOnEndScrollListener = mOnEndScrollListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(width), MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(height), MeasureSpec.EXACTLY);


        View containerPanel = getChildAt(0);
        containerPanel.measure(childWidthMeasureSpec, childHeightMeasureSpec);

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View containerPanel = getChildAt(0);
        containerPanel.layout(0,0, containerPanel.getMeasuredWidth(), containerPanel.getMeasuredHeight());

        setInitialPosition();
    }

    private void setInitialPosition() {
        ContainerPanel containerPanel = ((ContainerPanel)getChildAt(0));

        int childSize = containerPanel.getChildAt(0).getMeasuredWidth();
        smoothScrollTo(childSize * (currentPosition), 0);

    }
}
