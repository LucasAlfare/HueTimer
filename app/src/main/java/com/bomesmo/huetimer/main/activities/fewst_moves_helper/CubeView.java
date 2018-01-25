package com.bomesmo.huetimer.main.activities.fewst_moves_helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.puzzles.scrambles.rubiks.Cube;
import com.bomesmo.huetimer.main.puzzles.scrambles.rubiks.Sticker;

/**
 * Created by Lucas Sousa in 23/01/2018.
 *
 * View simples para representar um cubo mágico 3x3x3 planificado.
 */
public class CubeView extends View {

    private Cube cube;
    private String sequence;
    private float sqSize = 10;

    public CubeView(Context context, Cube cube) {
        super(context);
        this.cube = cube;
    }

    public CubeView(Context context, AttributeSet attrs, Cube cube) {
        super(context, attrs);
        this.cube = cube;

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CubeView);
        CharSequence foo_cs = arr.getString(R.styleable.CubeView_sequence);

        if (foo_cs != null) {
            // Do something with foo_cs.toString()
            this.cube.applySequence(foo_cs.toString());
        }

        arr.recycle();  // Do this when done.
    }

    public CubeView(Context context) {
        super(context);
        this.cube = new Cube();
    }

    public CubeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.cube = new Cube();

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CubeView);
        CharSequence foo_cs = arr.getString(R.styleable.CubeView_sequence);
        if (foo_cs != null) {
            // Do something with foo_cs.toString()
            this.cube.applySequence((String) foo_cs);
        }

        arr.recycle();  // Do this when done.
    }

    public CubeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.cube = new Cube();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
          Desenhando as 6 faces do cubo
         */

        /*azul     */ drawCubeFace(cube.getB(), 3 * sqSize, 0 * sqSize, canvas);
        /*laranja  */ drawCubeFace(cube.getL(), 0 * sqSize, 3 * sqSize, canvas);
        /*branco   */ drawCubeFace(cube.getU(), 3 * sqSize, 3 * sqSize, canvas);
        /*vermelho */ drawCubeFace(cube.getR(), 6 * sqSize, 3 * sqSize, canvas);
        /*amarelo  */ drawCubeFace(cube.getD(), 9 * sqSize, 3 * sqSize, canvas);
        /*verde    */ drawCubeFace(cube.getF(), 3 * sqSize, 6 * sqSize, canvas);
    }

    private void drawCubeFace(Sticker[] face, float x, float y, Canvas canvas){
        int colorCount = 0;

        for (int i = (int) x; i < x + (sqSize * 3); i += sqSize){
            for (int j = (int) y; j < y + (sqSize * 3); j += sqSize){
                Paint p = new Paint();

                //fills the current square using target color
                p.setColor(face[colorCount].getColor());
                p.setStyle(Paint.Style.FILL);
                canvas.drawRect(i, j, i + sqSize, j + sqSize, p);

                //set a black stroke around the filled square
                p.setStrokeWidth(1);
                p.setColor(Color.BLACK);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(i, j, i + sqSize, j + sqSize, p);

                colorCount++;
            }
        }

        Paint p = new Paint();

        p.setStrokeWidth(2);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + (sqSize * 3), y + (sqSize * 3), p);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        sqSize = w / 12;
    }

    @Override
    public int getMinimumWidth() {
        return 12 * (int) sqSize + 1;
    }

    @Override
    public int getMinimumHeight() {
        return 9 * (int) sqSize + 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 12 * (int) sqSize + 1;
        int desiredHeight = 9 * (int) sqSize + 1;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        this.setMeasuredDimension(width, height);
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }
}
