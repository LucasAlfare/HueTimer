package com.bomesmo.huetimer.main.activities.square1_csp_trainer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bomesmo.huetimer.main.R;

/**
 * Created by Lucas on 09/02/2018.
 */
public class CustomSpinnerAdapter extends BaseAdapter {

    private Context context;
    private CspTrainerActivity.SpinnerShape[] values;

    public CustomSpinnerAdapter(Context context, CspTrainerActivity.SpinnerShape[] values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.shapes_spinner_layout, parent, false);
        }

        TextView nameLabel = convertView.findViewById(R.id.shapeNameTv);
        ImageView shapeImage = convertView.findViewById(R.id.shapeImageView);

        nameLabel.setText(values[position].getShape().getName());
        shapeImage.setImageDrawable(convertView.getResources().getDrawable(values[position].getDrawableResource()));

        return convertView;
    }
}
