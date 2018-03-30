package com.avicsafety.ShenYangTowerComService.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/3.
 */

public class TabContainerView extends LinearLayout {
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    /** 默认颜色*/
    private int mTextNormalColor;
    /** 选中时颜色值*/
    private int mTextSelectedColor;

    /** 前一次选择位置*/
    private int mLastPosition;
    /** 当前选中位置*/
    private int mSelectedPosition;
    /**选择偏移位置 */
    private float mSelectionOffset;

    /** tab 标题*/
    private String[] mTitles;
    /** tab icon集合*/
    private int[][] mIconRes;

    /** tab item 视图集合*/
    private View[] mTabView;

    /** 布局文件id*/
    private int mLayoutId;
    /** textView 控件id*/
    private int mTextViewId;
    /** icon 控件id*/
    private int mIconVIewId;

    /**icon宽度*/
    private int mIconWidth;
    /**icon高度*/
    private int mIconHeight;

    private  Resources.Theme theme;

    /** 是否显示过渡颜色效果*/
    private boolean mShowTransitionColor = true;

    public TabContainerView(Context context) {
        this(context, null);
    }

    public TabContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 自定义实现底部bar容器
     * 定义外用接口，实现bar显示资源的信息
     * @param titles
     * @param iconsRes
     * @param colors
     * @param showTransitionColor
     */
    public void initContainer (String[] titles, int[][] iconsRes, int[] colors, boolean showTransitionColor) {
        this.mTitles = titles;
        this.mIconRes = iconsRes;
        this.mTextNormalColor = ContextCompat.getColor(this.getContext(),colors[0]);
        this.mTextSelectedColor = ContextCompat.getColor(this.getContext(),colors[1]);
        this.mShowTransitionColor = showTransitionColor;
    }

    /**
     * 设置布局文件及相关控件id
     * @param layout layout布局文件 id
     * @param iconId ImageView 控件 id id <=0 时不显示
     * @param textId TextView 控件 id id <=0 时不显示
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setContainerLayout (int layout, int iconId, int textId, int width, int height) {
        mLayoutId = layout;
        mTextViewId = textId;
        mIconVIewId = iconId;
        mIconWidth = width;
        mIconHeight = height;
    }

    /**
     * 设置布局文件及相关控件id --只有文本的时候
     * @param layout layout布局文件 id
     * @param textId TextView 控件 id
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setSingleTextLayout (int layout, int textId, int width, int height) {
        setContainerLayout(layout, 0, textId, width, height);

    }

    /**
     * 设置布局文件及相关控件id --只有图片的时候
     * @param layout layout布局文件 id
     * @param iconId ImageView 控件 id
     * @param width  icon 宽度
     * @param height icon 高度
     */
    public void setSingleIconLayout (int layout, int iconId, int width, int height) {
        setContainerLayout(layout, iconId, 0, width,  height);
    }

    public void setViewPager(ViewPager viewPager) {
        //移除所有的子视图
        removeAllViews();
        mViewPager = viewPager;
        if (viewPager != null && viewPager.getAdapter() != null) {
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            addTabViewToContainer();
        }
    }

    /**
     * 添加tab view到当前容器
     */
    private void addTabViewToContainer() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        mTabView = new View[adapter.getCount()];

        for (int index = 0, len = adapter.getCount(); index < len; index++) {

            final View tabView = LayoutInflater.from(getContext()).inflate(mLayoutId, this, false);
            mTabView[index] = tabView;

            /*tabIconView初始化*/
            TabIconView iconView = null;
            if (mIconVIewId > 0) {
                iconView = (TabIconView) tabView.findViewById(mIconVIewId);
                iconView.init(mIconRes[index][0], mIconRes[index][1], mIconWidth, mIconHeight);
            }

            /*tabTextView初始化*/
            TextView textView = null;
            if (mTextViewId > 0) {
                textView = (TextView) tabView.findViewById(mTextViewId);
                textView.setText(mTitles[index]);

            }

            /*设置宽度，等分container*/
            LayoutParams lp = (LayoutParams) tabView.getLayoutParams();
            lp.width = 0;
            lp.weight = 1;

            /*添加tab点击事件*/
            addTabOnClickListener(tabView, index);

            /*设置当前状态*/
            if (index == mViewPager.getCurrentItem()) {
                if (iconView != null) {
                    iconView.offsetChanged(0);
                }
                tabView.setSelected(true);
                if (textView != null) {
                    textView.setTextColor(mTextSelectedColor);
                }
            }

            addView(tabView);
        }
    }

    /**
     * viewPager滑动改变监听事件
     */
    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        /**
         * 页面滑动停止之前，此方法一直得到调用
         * @param position  当前页面，及你当前点击的页面
         * @param positionOffset  当前页面偏移的百分比
         * @param positionOffsetPixels  当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            onViewPagerPageChanged(position, positionOffset);

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }
        //跳转完成 position 当前选中的页面
        @Override
        public void onPageSelected(int position) {

            /*完成滑动动作后更新每个Item TextView的颜色值以及ImageView的状态值*/
            for (int i = 0; i < getChildCount(); i++) {
                if (mIconVIewId > 0) {
                    ((TabIconView) mTabView[i].findViewById(mIconVIewId)) .offsetChanged(position == i ? 0 : 1);
                }
                if (mTextViewId > 0) {
                    ((TextView) mTabView[i].findViewById(mTextViewId)) .setTextColor(position == i ? mTextSelectedColor : mTextNormalColor);
                }
            }

            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                onViewPagerPageChanged(position, 0f);
            }

            for (int i = 0, size = getChildCount(); i < size; i++) {
                getChildAt(i).setSelected(position == i);
            }


            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }
        //状态改变过程 state分别为0,1，2  1表示正在滑动  2  表示滑动结束  3表示什么都没有做 页面开始滑动的过程分别是1，2，0
        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    }

    /**
     * viewpager滑动改变后更新当前container视图
     * @param position 当前选择position
     * @param positionOffset position 偏移量
     */
    private void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        if (positionOffset == 0f && mLastPosition != mSelectedPosition) {
            mLastPosition = mSelectedPosition;
        }
        invalidate();
    }

    /**
     * 发生偏移量的时候重新绘制底部bar容器
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int childCount = getChildCount();
        if (childCount > 0) {
            /*当发生偏移时，绘制渐变区域*/
            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1) && mShowTransitionColor) {

                /*获取当前tab和下一tab view */
                View selectedTab = getChildAt(mSelectedPosition);
                View nextTab = getChildAt(mSelectedPosition + 1);

                /*显示tab icon时,刷新各自view 透明度*/
                if (mIconVIewId > 0) {
                    View selectedIconView = selectedTab.findViewById(mIconVIewId);
                    View nextIconView = nextTab.findViewById(mIconVIewId);

                    //draw icon alpha
                    if (selectedIconView instanceof TabIconView && nextIconView instanceof TabIconView) {
                        ((TabIconView) selectedIconView).offsetChanged(mSelectionOffset);
                        ((TabIconView) nextIconView).offsetChanged(1 - mSelectionOffset);
                    }
                }

                 /*显示tab text,刷新各自view 透明度*/
                if  (mTextViewId > 0) {
                    View selectedTextView = selectedTab.findViewById(mTextViewId);
                    View nextTextView = nextTab.findViewById(mTextViewId);

                    //draw text color
                    Integer selectedColor = (Integer) evaluate(mSelectionOffset, mTextSelectedColor, mTextNormalColor);
                    Integer nextColor = (Integer) evaluate(1 - mSelectionOffset, mTextSelectedColor, mTextNormalColor);

                    if (selectedTextView instanceof TextView && nextTextView instanceof TextView) {
                        ((TextView) selectedTextView).setTextColor(selectedColor);
                        ((TextView) nextTextView).setTextColor(nextColor);
                    }
                }

            }
        }
    }

    /**
     * tab item点击事件,点击时直接跳到对应view
     * @param view        View
     * @param position position
     */
    private void addTabOnClickListener (View view, final int position) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(position, false);
            }
        };
        view.setOnClickListener(listener);
    }


    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * 返回计算的颜色的中间值给定表示四个中的开始和结束值的整数32位int的字节。
     * 每个通道被单独线性内插并将所得的计算值重新组合到返回值中。
     * @param fraction  从开始到结束值的分数
     * @param startValue  表示中的颜色的32位int值参数的单独字节
     * @param endValue  表示中的颜色的32位int值参数的单独字节
     * @return  计算为线性插值的值结果，通过将开始和结束值分离为单独的结果颜色通道和单独插值，重新组合结果值以相同的方式
     */
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (startA + (int)(fraction * (endA - startA))) << 24 |
                (startR + (int)(fraction * (endR - startR))) << 16 |
                (startG + (int)(fraction * (endG - startG))) << 8 |
                (startB + (int)(fraction * (endB - startB)));
    }
}
