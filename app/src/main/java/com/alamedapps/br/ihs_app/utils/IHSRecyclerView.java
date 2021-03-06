package com.alamedapps.br.ihs_app.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class IHSRecyclerView implements RecyclerView.OnItemTouchListener {


    private ItemTouch myListener;
    private GestureDetector gestureDetector;

    public IHSRecyclerView(Context context, final RecyclerView view, ItemTouch listener){
        myListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                super.onSingleTapUp(motionEvent);

                View childView = view.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(childView != null && myListener != null){
                    myListener.clickSimples(childView, view.getChildAdapterPosition(childView));
                }

                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ItemTouch{
        void clickSimples(View view, int position);
    }
}
