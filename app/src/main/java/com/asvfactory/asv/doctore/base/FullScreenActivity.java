package com.asvfactory.asv.doctore.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

/**
 * Created by Alberto on 29/11/2015.
 */
public class FullScreenActivity extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        hideActionBar();
    }

    //asv falta hacer q se utilize toda la pantalla para dibujar el activity, oculta la status y la toolbar xo utiliza el espacio
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideActionBar() {
        View decorView = this.getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        android.app.ActionBar actionBar = this.getActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

}
