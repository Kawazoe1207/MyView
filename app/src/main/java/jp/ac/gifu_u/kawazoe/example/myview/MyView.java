package jp.ac.gifu_u.kawazoe.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    private ArrayList<Integer> array_x, array_y;
    private ArrayList<Boolean> array_status;

    public MyView(Context context) {
        super(context);
        array_x = new ArrayList<>();
        array_y = new ArrayList<>();
        array_status = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                array_x.add(x);
                array_y.add(y);
                array_status.add(false); // 線を引かない
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                array_x.add(x);
                array_y.add(y);
                array_status.add(true); // 線を引く
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 背景白
        Paint bg = new Paint();
        bg.setStyle(Paint.Style.FILL);
        bg.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), bg);

        // 赤線の設定
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        // 線の描画（status=trueのとき）
        for (int i = 1; i < array_status.size(); i++) {
            if (array_status.get(i)) {
                int x1 = array_x.get(i - 1);
                int y1 = array_y.get(i - 1);
                int x2 = array_x.get(i);
                int y2 = array_y.get(i);
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }
}
