package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.auxiliar.TF;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.Solve;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.configs_crud.ReadConfig;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud.Delete;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud.Read;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToSwipedDirection;

import java.util.ArrayList;

/**
 * Created by Lucas on 23/03/2018.
 */

public class SwipeAdapter extends RecyclerView.Adapter<ItemViewHolder> implements SwipeableItemAdapter<ItemViewHolder> {

    private Context context;
    private ArrayList<Solve> solves;

    public SwipeAdapter(Context context) {
        this.context = context;

        this.solves = new Read().getSolves();
        this.setHasStableIds(true);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.solve_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (solves.get(position).isDNF()) {
            holder.swipeTime.setText("DNF");
        } else if (solves.get(position).isPlus2()) {
            holder.swipeTime.setText("+" + TF.longToTimestamp(solves.get(solves.size() - 1).getPhasesTimes().get(new ReadConfig().getGlobalConfiguration().getNumPhases() - 1) + 2000));
        } else {
            holder.swipeTime.setText(TF.longToTimestamp(solves.get(position).getTotalSolveTime()));
        }

        holder.swipeScramble.setText(solves.get(position).getScramble());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clique no item!! (container rsrsrs...)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return solves.get(position).getUuid().hashCode();
    }

    @Override
    public int getItemCount() {
        return solves.size();
    }

    @Override
    public int onGetSwipeReactionType(ItemViewHolder holder, int position, int x, int y) {
        return SwipeableItemConstants.REACTION_CAN_SWIPE_LEFT;
    }

    @Override
    public void onSwipeItemStarted(ItemViewHolder holder, int position) {
        notifyDataSetChanged();
    }

    @Override
    public void onSetSwipeBackground(ItemViewHolder holder, int position, int type) {
//        if (type == SwipeableItemConstants.DRAWABLE_SWIPE_LEFT_BACKGROUND) {
//            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
//        } else {
//            holder.itemView.setBackgroundColor(Color.parseColor("#d7ccc8"));
//        }
    }

    @Override
    public SwipeResultAction onSwipeItem(ItemViewHolder holder, final int position, int result) {
        if (result == SwipeableItemConstants.RESULT_SWIPED_LEFT) {
            return new SwipeResultActionMoveToSwipedDirection() {
                @Override
                protected void onPerformAction() {
                    super.onPerformAction();

                    new Delete().removerSolve(solves.get(position));
                    solves.remove(position);
                    notifyItemRemoved(position);
                }
            };
        }

        return new SwipeResultActionDefault();
    }
}
