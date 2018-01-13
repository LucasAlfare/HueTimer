package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.activities.TimesListActivity;

import java.util.ArrayList;

/**
 * Created by Lucas on 25/12/2017.
 */

public class AnimatedExpandableListViewAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private Context context;
    private TimesListActivity timesListActivity;
    private ArrayList<Solve> solves;

    public AnimatedExpandableListViewAdapter(Context context, TimesListActivity timesListActivity, ArrayList<Solve> solves) {
        this.context = context;
        this.timesListActivity = timesListActivity;
        this.solves = solves;
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.solves_list_group_item, parent, false);
        }

        TextView solveTime = convertView.findViewById(R.id.checkSolve);
        if (!solves.isEmpty()) solveTime.setText((groupPosition + 1) + ")\t" + TF.longToTimestamp(solves.get(groupPosition).getTime()));

        return convertView;
    }

    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.solves_list_subgroup_item, parent, false);
        }

        ImageButton editSolve = convertView.findViewById(R.id.editSolve);
        ImageButton deleteSolve = convertView.findViewById(R.id.deleteSolve);
        ImageButton shareSolve = convertView.findViewById(R.id.shareSolve);

        final EditText scrambleBox = convertView.findViewById(R.id.scrambleBox);
        scrambleBox.setText(solves.get(groupPosition).getScramble());

        scrambleBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrambleBox.selectAll();
            }
        });

        editSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(timesListActivity);

                alert.setTitle("Editar solve");
                alert.setMessage("Por favor, edite a solve");

                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                final View view = layoutInflater.inflate(R.layout.alert_diolog_edit_solve, parent, false);

                alert.setView(view);

                final EditText tempo = view.findViewById(R.id.newTime);
                tempo.setText(TF.longToTimestamp(solves.get(groupPosition).getTime()));

                final EditText scramble = view.findViewById(R.id.newScramble);
                scramble.setText(solves.get(groupPosition).getScramble());

                alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        Solve newValue = new Solve(
                                                    TF.timestampToLong(tempo.getText().toString()),
                                                    scramble.getText().toString());

                        /*
                          Solve newValue = new Solve(
                         TF.timestampToLong(tempo.getText().toString()),
                         scramble.getText().toString());
                         */

                        SolvesHandler.setSolve(context, groupPosition, newValue);
                        Toast.makeText(context, "Editado!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // pass
                    }
                });

                alert.show();
            }
        });

        deleteSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(timesListActivity);

                alert.setTitle("ATENÇÃO!!");
                alert.setMessage("Excluir o tempo " + TF.longToTimestamp(solves.get(groupPosition).getTime()) + "???");

                alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SolvesHandler.removeSolve(context, groupPosition);
                        Toast.makeText(timesListActivity, "deletado!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                alert.show();
            }
        });

        shareSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                String value =
                        "TEXTO GERADO PELO HUETIMER!! afff kkkk" + "\n\n" +
                        "Tempo: " + TF.longToTimestamp(solves.get(groupPosition).getTime()) + "\n\n" +
                        "Embaralhamento: " + solves.get(groupPosition).getScramble();

                shareIntent.putExtra(Intent.EXTRA_TEXT, value);
                shareIntent.setType("text/plain");

                timesListActivity.startActivity(shareIntent);

                /* Alternativo...

                startActivity(Intent.createChooser(share, "Share using"));
                 */
            }
        });

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        //return 1;
        return (solves.size() - solves.size()) + 1;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
