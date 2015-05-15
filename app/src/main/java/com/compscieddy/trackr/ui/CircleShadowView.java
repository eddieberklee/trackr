package com.compscieddy.trackr.ui;

/**
 * Created by elee on 5/15/15.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.compscieddy.trackr.R;
import com.compscieddy.trackr.util.Util;
import com.compscieddy.trackr.util.ViewUtil;

public class CircleShadowView extends TextView {

  public static final String TAG = CircleShadowView.class.getSimpleName();

  private int mCircleColor = getResources().getColor(R.color.white);
  private int mShadowStartColor = getResources().getColor(R.color.black);
  private int mShadowEndColor = getResources().getColor(R.color.transparent);
  private int mCirclePadding = -1;
  private int mShadowDistance = -1;
  private LayerDrawable mLayerDrawable;
  private GradientDrawable mShadowDrawable;

  private final int CIRCLE_LAYER_INDEX = 1;

  public CircleShadowView(Context context) {
    this(context, null);
  }

  public CircleShadowView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public CircleShadowView(Context context, AttributeSet attrs, int defStyle) {
    this(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {

//    LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleShadowView);
    try {
      mCircleColor = typedArray.getColor(R.styleable.CircleShadowView_circleColor, R.color.white);
      mShadowStartColor = typedArray.getColor(R.styleable.CircleShadowView_shadowStartColor, R.color.black);
      mShadowEndColor = typedArray.getColor(R.styleable.CircleShadowView_shadowEndColor, R.color.transparent);
      mCirclePadding = typedArray.getDimensionPixelOffset(R.styleable.CircleShadowView_circlePadding, -1);
      mShadowDistance = typedArray.getDimensionPixelOffset(R.styleable.CircleShadowView_shadowDistance, -1);
    } finally {
      typedArray.recycle();
    }

    setSingleLine(true);
    setGravity(Gravity.CENTER);

    Drawable circleDrawable = getResources().getDrawable(R.drawable.white_circle);
    ViewUtil.applyColorFilter(circleDrawable, mCircleColor);

    int[] shadowColors = new int[] { getResources().getColor(R.color.black), getResources().getColor(R.color.transparent) };
    mShadowDrawable = new GradientDrawable();
//    shadowDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
    mShadowDrawable.setShape(GradientDrawable.OVAL);
    mShadowDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//    shadowDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
    mShadowDrawable.setGradientCenter(0.5f, 0.5f);
    mShadowDrawable.setColors(shadowColors);

    /*GradientDrawable shadowDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { getResources().getColor(R.color.white), Color.rgb(255, 0, 0), Color.BLUE });
    shadowDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
    shadowDrawable.setGradientRadius(140.0f);
    shadowDrawable.setGradientCenter(0.0f, 0.45f);*/

    Drawable[] drawableLayers = new Drawable[] {mShadowDrawable, circleDrawable };
    mLayerDrawable = new LayerDrawable(drawableLayers);
    int shadowPadding = mShadowDistance;
    mLayerDrawable.setLayerInset(CIRCLE_LAYER_INDEX, shadowPadding, shadowPadding, shadowPadding, shadowPadding);
    setEllipsize(TextUtils.TruncateAt.END);

    setBackgroundDrawable(mLayerDrawable);
  }

  private final float MIN_SHADOW_PADDING_RATIO = 0.2f; // padding from shadow circumference to circle
  private final float MIN_CIRCLE_PADDING_RATIO = 0.3f; // padding from circle circumference to text

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int measuredWidth = getMeasuredWidth();
    int minShadowPadding = (int) (measuredWidth * MIN_SHADOW_PADDING_RATIO);
    int padding = (int) (MIN_CIRCLE_PADDING_RATIO * measuredWidth) + mCirclePadding;
    mShadowDrawable.setGradientRadius((2 * padding + measuredWidth + mShadowDistance) / 2);
    if (mShadowDistance == -1) {
      // still set a minimum shadow distance
      mLayerDrawable.setLayerInset(CIRCLE_LAYER_INDEX, minShadowPadding, minShadowPadding, minShadowPadding, minShadowPadding);
    }
    setMeasuredDimension(2 * padding + measuredWidth + mShadowDistance + minShadowPadding, 2 * padding + measuredWidth + mShadowDistance + minShadowPadding);
  }

}
