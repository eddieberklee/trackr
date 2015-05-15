package com.compscieddy.trackr.util;

import android.content.res.Resources;

/**
 * Created by elee on 5/15/15.
 */
public class Util {


  public static int convertToPx(float dp) {
    return Math.round(dp * Resources.getSystem().getDisplayMetrics().density);
  }

}
