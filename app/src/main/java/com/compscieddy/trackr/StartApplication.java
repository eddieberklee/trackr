package com.compscieddy.trackr;

import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;

/**
 * Created by Josh on 5/13/2015.
 */
public class StartApplication extends Application {

    public void onCreate () {

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "HIFJftTa07ItuI2gku6Z9J79SyDj3N7XoNTNb7UK", "SC9S7DcvYUFZImFBM3bTYZePnAWpmc9YWS9u7yi3");
    }
}