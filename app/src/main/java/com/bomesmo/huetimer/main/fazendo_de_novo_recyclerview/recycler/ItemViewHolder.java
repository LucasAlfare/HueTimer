package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.recycler;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.R;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;

/**
 * Created by Lucas on 23/03/2018.
 */

public class ItemViewHolder extends AbstractSwipeableItemViewHolder {

    public RelativeLayout container;
    public TextView swipeTime;
    public EditText swipeScramble;

    public ItemViewHolder(View itemView) {
        super(itemView);

        container = itemView.findViewById(R.id.swipe_container);
        swipeTime = itemView.findViewById(R.id.swipe_time);
        swipeScramble = itemView.findViewById(R.id.swipe_scramble);
    }

    @Override
    public View getSwipeableContainerView() {
        return container;
    }
}
