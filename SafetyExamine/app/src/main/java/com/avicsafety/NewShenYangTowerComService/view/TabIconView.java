package com.avicsafety.NewShenYangTowerComService.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/2.
 */

public class TabIconView extends ImageView {
    /** 改变透明度 */
    private Paint mPaint;
    /** focus下bitmap*/
    private Bitmap mSelectedIcon;
    /** normal下bitmap*/
    private Bitmap mNormalIcon;
    /** focus bitmap矩阵*/
    private Rect mSelectedRect;
    /** normal bitmap矩阵*/
    private Rect mNormalRect;
    /** 当前选择项(mSelectedIcon)透明度
     *  mNormalIcon 透明度即为 255 - mSelectedAlpha */
    private int mSelectedAlpha = 0;

    public TabIconView(Context context) {
        super(context);
    }

    public TabIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化资源图片bitmap及获取Paint对象
     * @param normal normals
     * @param selected focus
     */
    public final void init(int normal, int selected, int width, int height) {
        this.mNormalIcon = createBitmap(normal);
        this.mSelectedIcon = createBitmap(selected);
        this.mNormalRect = new Rect(0, 0, width, height);
        this.mSelectedRect = new Rect(0, 0, width, height);
        this.mPaint = new Paint(1);
    }

    /**
     * 通过 id加载图片
     * @param resId
     * @return
     */
    private Bitmap createBitmap(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    /**
     * 重新绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPaint == null) {
            return;
        }
        this.mPaint.setAlpha(255 - this.mSelectedAlpha);
        canvas.drawBitmap(this.mNormalIcon, null, this.mNormalRect, this.mPaint);
        this.mPaint.setAlpha(this.mSelectedAlpha);
        canvas.drawBitmap(this.mSelectedIcon, null, this.mSelectedRect, this.mPaint);
    }

    /**
     * 改变透明度值
     * @param alpha 透明度
     */
    public final void changeSelectedAlpha(int alpha) {
        this.mSelectedAlpha = alpha;
        //窗口客户区无效，需要重新重新绘制窗口
        invalidate();
    }

    /**
     * 改变透明度百分比
     * @param offset 百分比
     */
    public final void offsetChanged(float offset) {
        changeSelectedAlpha((int) (255 * (1 - offset)));
    }
}
