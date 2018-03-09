package com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.Solve;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Delete;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Read;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Update;
import com.bomesmo.huetimer.main.fazendo_de_novo.fragments.SolvesFragment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lucas on 07/03/2018.
 */
public class AdapterNovo extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    ArrayList<Solve> solves;
    private Context context;

    public AdapterNovo(Context context) {
        this.context = context;
        solves = new Read().getSolves();
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        //return (MainActivity2.solves.size() - MainActivity2.solves.size()) + 1;
        return 1;
    }

    @Override
    public int getGroupCount() {
        return solves.size();
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
        View groupLayoutXML = LayoutInflater.from(getContext()).inflate(R.layout.solves_list_group_item, parent, false);

        Solve currentSolve = solves.get(groupPosition);

        String groupStamp = (groupPosition + 1) + ")\t";
        if (currentSolve.isDNF()){
            groupStamp += "DNF";
        } else if (currentSolve.isPlus2()){
            groupStamp += "+";
            //groupStamp += TF.longToTimestamp(MainActivity2.solves.get(groupPosition).getPhasesTimes().get(MainActivity2.solves.size() - 1) + 2000);
            groupStamp += TF.longToTimestamp(solves.get(groupPosition).getTotalSolveTime() + 2000);
        } else {
            //groupStamp += TF.longToTimestamp(MainActivity2.solves.get(groupPosition).getPhasesTimes().get(MainActivity2.numPhases - 1));
            groupStamp += TF.longToTimestamp(solves.get(groupPosition).getTotalSolveTime());
        }

        TextView solveGroupLabel = groupLayoutXML.findViewById(R.id.solveGroupLabel);
        solveGroupLabel.setText(groupStamp);

        return groupLayoutXML;
    }

    @Override
    public View getRealChildView(final int groupPosition, int childPosition, boolean isLastChild, final View convertView, final ViewGroup parent) {
        View childLayoutXML = LayoutInflater.from(getContext()).inflate(R.layout.solves_list_subgroup_item, parent, false);

        //INSTANTIATIONS
        EditText scrambleChild = childLayoutXML.findViewById(R.id.scrambleChild);
        ImageButton editar = childLayoutXML.findViewById(R.id.editSolve);
        final ImageButton deletar = childLayoutXML.findViewById(R.id.deleteSolve);
        ImageButton compartilhar = childLayoutXML.findViewById(R.id.shareSolve);

        StringBuilder builder = new StringBuilder();
        builder.append("Times of the solve:\n");

        for (int i = 0; i < solves.get(groupPosition).getPhasesTimes().size(); i++) {
            builder.append("phase ").append(i + 1).append(": ");
            builder.append(TF.longToTimestamp(solves.get(groupPosition).getPhasesTimes().get(i)));

            if (i != solves.get(groupPosition).getPhasesTimes().size()){
                builder.append(", ");
            }
        }

        builder.append("\n\nScramble:\n");
        builder.append(solves.get(groupPosition).getScramble());

        scrambleChild.setText(builder.toString());

        //TODO
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View alertView = LayoutInflater.from(context).inflate(R.layout.alert_edit_solve, parent, false);

                final AlertDialog.Builder teste = new AlertDialog.Builder(context);
                teste.setTitle("Editting " + TF.longToTimestamp(solves.get(groupPosition).getTotalSolveTime()));
                teste.setView(alertView);

                teste.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RadioGroup radioGroup = alertView.findViewById(R.id.childRadioGroup);
                        EditText newTime = alertView.findViewById(R.id.newTime);
                        EditText newScramble = alertView.findViewById(R.id.newScramble);
                        solves = new Read().getSolves();

                        Solve x = solves.get(groupPosition);
                        if (!newTime.getText().toString().equals("")){
                            x.setTime(new ArrayList<>(Collections.singletonList(TF.timestampToLong(newTime.getText().toString()))));
                        }

                        if (!newScramble.getText().toString().equals("")){
                            x.setScramble(newScramble.getText().toString());
                        }

                        View radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                        int index = radioGroup.indexOfChild(radioButton);

                        if (index == 0){
                            x.setPlus2(false);
                            x.setDNF(false);
                        } else if (index == 1){
                            x.setPlus2(true);
                            x.setDNF(false);
                        } else {
                            x.setPlus2(false);
                            x.setDNF(true);
                        }

                        new Update().updateSolve(x);
                        SolvesFragment.animatedListView.setAdapter(new AdapterNovo(context));
                        Toast.makeText(context, "Saved.", Toast.LENGTH_SHORT).show();
                    }
                });

                teste.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                teste.show();
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                deleteAlert.setTitle("Delete the time " + TF.longToTimestamp(solves.get(groupPosition).getTotalSolveTime()) + "??");

                deleteAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (solves.size() > 0) {
                            Solve target = solves.get(groupPosition);

                            if (new Delete().removerSolve(target)) {
                                Toast.makeText(context, "DELETED!", Toast.LENGTH_SHORT).show();
                                SolvesFragment.animatedListView.setAdapter(new AdapterNovo(context));
                            } else {
                                Toast.makeText(context, "Something wrong happened...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                deleteAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                deleteAlert.show();
            }
        });

        compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "not implemented yet...", Toast.LENGTH_SHORT).show();
            }
        });

        return childLayoutXML;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Solve> getSolves() {
        return solves;
    }

    public void setSolves(ArrayList<Solve> solves) {
        this.solves = solves;
    }
}
