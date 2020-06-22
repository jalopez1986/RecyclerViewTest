package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OneByOneRecyclerView extends RecyclerView {
    public OneByOneRecyclerView(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        System.out.println("Jorge2 velocityX: " + velocityX);


        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();

        //int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenWidth = this.getMeasuredWidth();


        // views on the screen
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        View lastView = linearLayoutManager.findViewByPosition(lastVisibleItemPosition);
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        View firstView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition);

        int numberOfCards = Math.abs(lastVisibleItemPosition - firstVisibleItemPosition);
        System.out.println(numberOfCards);

        screenWidth = screenWidth / numberOfCards;



        // distance we need to scroll
        int leftMargin = (screenWidth - lastView.getWidth()) / 2;
        int rightMargin = (screenWidth - firstView.getWidth()) / 2 + firstView.getWidth();
        int leftEdge = lastView.getLeft();
        int rightEdge = firstView.getRight();
        int scrollDistanceLeft = leftEdge - leftMargin;
        int scrollDistanceRight = rightMargin - rightEdge;


        if (Math.abs(velocityX) < 1000) {
            // The fling is slow -> stay at the current page if we are less than half through,
            // or go to the next page if more than half through

            if (leftEdge > screenWidth / 2) {
                // go to next page
                smoothScrollBy(-scrollDistanceRight, 0);
            } else if (rightEdge < screenWidth / 2) {
                // go to next page
                smoothScrollBy(scrollDistanceLeft, 0);
            } else {
                // stay at current page
                if (velocityX > 0) {
                    smoothScrollBy(-scrollDistanceRight, 0);
                } else {
                    smoothScrollBy(scrollDistanceLeft, 0);
                }
            }
//            handleActions();
            return true;

        } else {
            // The fling is fast -> go to next page

            if (velocityX > 0) {
                smoothScrollBy(scrollDistanceLeft, 0);
            } else {
                smoothScrollBy(-scrollDistanceRight, 0);
            }
//            handleActions();
            return true;

        }

    }




}
