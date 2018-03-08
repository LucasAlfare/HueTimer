package com.bomesmo.huetimer.main.activities.square1_csp_trainer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.cs.main.puzzle.Cube;
import com.cs.main.puzzle.Shape;
import com.cs.main.puzzle.SquareUtils;

import java.util.Objects;

public class CspTrainerActivity extends AppCompatActivity {

    private Spinner spinnerTop, spinnerBottom;
    private Button newScramble;

    enum SpinnerShape {
        SQUARE(new Shape("square", "011011011011"), R.drawable.ic_shape_square),
        KITE(new Shape("kite", "001101111011"), R.drawable.ic_kite),
        BARREL(new Shape("barrel", "001111001111"), R.drawable.ic_shape_barrel),
        MUSHROOM(new Shape("mushroom", "101111000111"), R.drawable.ic_shape_mushroom),
        SHIELD(new Shape("shield", "111001100111"), R.drawable.ic_shape_shield),
        LEFT_FIST(new Shape("left fist", "011011001111"), R.drawable.ic_shape_leftfist),
        RIGHT_FIST(new Shape("right fist", "101111001101"), R.drawable.ic_shape_rightfist),
        RIGHT_PAWN(new Shape("right pawn", "110110001111"), R.drawable.ic_shape_rightpawn),
        LEFT_PAWN(new Shape("left pawn", "011011111100"), R.drawable.ic_shape_leftpawn),
        SCALLOP(new Shape("scallop", "111100001111"), R.drawable.ic_sahpe_scallop),

        PAIRED(new Shape("paired", "111111111100"), R.drawable.ic_shape_paired),
        PERPENDICULAR(new Shape("perpendicular", "111111011011"), R.drawable.ic_shape_perpendicular),
        PARALLEL(new Shape("parallel", "111111011110"), R.drawable.ic_shape_parallel),
        STAR(new Shape("star", "111111111111"), R.drawable.ic_shape_star),

        _222(new Shape("2-2-2", "001100110011"), R.drawable.ic_shape__222),
        _33(new Shape("3-3", "000110001111"), R.drawable.ic_shape__33),
        _321(new Shape("3-2-1", "110110001100"), R.drawable.ic_shape__321),
        _312(new Shape("3-1-2", "110110011000"), R.drawable.ic_shape__312),
        LEFT42(new Shape("left 4-2", "001111000011"), R.drawable.ic_shape_left42),
        RIGHT42(new Shape("right 4-2", "110011000011"), R.drawable.ic_shape_right42),
        LEFT51(new Shape("left 5-1", "011011110000"), R.drawable.ic_shape_left51),
        RIGHT51(new Shape("right 5-1", "110000011110"), R.drawable.ic_shape_right51),
        _6(new Shape("6", "111100000011"), R.drawable.ic_shape__6),

        _44(new Shape("4-4", "000011000011"), R.drawable.ic_shape__44),
        _53(new Shape("5-3", "000110000011"), R.drawable.ic_shape__53),
        _62(new Shape("6-2", "000110011000"), R.drawable.ic_shape__62),
        _71(new Shape("7-1", "011011000000"), R.drawable.ic_shape__71),
        _8(new Shape("8", "000011110000"), R.drawable.ic_shape__8);

        private Shape shape;
        private int drawableResource;

        SpinnerShape(Shape shape, int drawableResource) {
            this.shape = shape;
            this.drawableResource = drawableResource;
        }

        public int getDrawableResource() {
            return drawableResource;
        }

        public void setDrawableResource(int drawableResource) {
            this.drawableResource = drawableResource;
        }

        public Shape getShape() {
            return shape;
        }

        public void setShape(Shape shape) {
            this.shape = shape;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csp_trainer);
        init();

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(
                getApplicationContext(),
                SpinnerShape.values()
        );

        spinnerTop.setAdapter(customSpinnerAdapter);
        spinnerBottom.setAdapter(customSpinnerAdapter);

        final ProgressDialog dialog = new ProgressDialog(CspTrainerActivity.this);
        dialog.setIndeterminate(true);

        newScramble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s = new String[1];
                dialog.show();

                if (dialog.isShowing()){
                    s[0] = SquareUtils.getRandomStateScrambleToShape(
                            SpinnerShape.values()[spinnerTop.getSelectedItemPosition()].getShape(),
                            SpinnerShape.values()[spinnerBottom.getSelectedItemPosition()].getShape(),
                            findViewById(R.id.sqExactCb).isSelected());
                }

                dialog.cancel();

                newScramble.setText(s[0]);
            }
        });

//        newScramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Cube cube;
//                final String[] s = new String[1];
//                s[0] = "oi";
//                final ProgressDialog progress = new ProgressDialog(CspTrainerActivity.this);
//                progress.setMessage("Procurando seu scramble...");
//                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progress.setIndeterminate(true);
//                //progress.setCancelable(false);
//
//                final Thread a = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        s[0] = SquareUtils.getRandomStateScrambleToShape(
//                                SpinnerShape.values()[spinnerTop.getSelectedItemPosition()].getShape(),
//                                SpinnerShape.values()[spinnerBottom.getSelectedItemPosition()].getShape(),
//                                findViewById(R.id.sqExactCb).isSelected());
//
//                        if (!s[0].equals("oi")){
//                            progress.cancel();
//
//                            newScramble.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    newScramble.setText(s[0]);
//                                }
//                            });
//                        }
//                    }
//                });
//
//                progress.show();
//                a.start();
//            }
//        });
    }

    private void init() {
        spinnerTop = findViewById(R.id.spinnerTop);
        spinnerBottom = findViewById(R.id.spinnerBottom);
        newScramble = findViewById(R.id.sqScramble);
    }
}
