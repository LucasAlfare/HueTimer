package com.bomesmo.huetimer.main.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bomesmo.huetimer.main.R;

public class TimesListActivity extends AppCompatActivity {

    /*
    checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
     */

    /*
    LayoutInflater inflater = LayoutInflater.from(context);
    RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.custom_layout, null, false);

    LinearLayout linear = (LinearLayout)findViewById(R.id.myLayout);
    linear.addView(layout);
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_list);
    }
}
