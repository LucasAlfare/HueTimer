package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.core.Solve;

import java.util.ArrayList;

/**
 * Created by Lucas on 25/12/2017.
 */

public class Adaptador extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Solve> solves;

    public Adaptador(Context context, ArrayList<Solve> solves) {
        this.context = context;
        this.solves = solves;
    }

    @Override
    public int getGroupCount() {
        return solves.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return solves.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return solves.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.solves_list_group_item, parent, false);
        }

        TextView solveTime = convertView.findViewById(R.id.checkSolve);
        solveTime.setText(TF.format(solves.get(groupPosition).getTime()));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.solves_list_subgroup_item, parent, false);
        }

        EditText scrambleBox = convertView.findViewById(R.id.scrambleBox);
        ImageButton deleteSolve = convertView.findViewById(R.id.deleteSolve);
        ImageButton shareSolve = convertView.findViewById(R.id.shareSolve);

        scrambleBox.setText(solves.get(childPosition).getScramble());

        deleteSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Deletado :)", Snackbar.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
