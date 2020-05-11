package com.nhandz.flrv_ch.DT;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextviewFont extends TextView {
    public TextviewFont(Context context) {
        super(context);
        init();
    }

    public TextviewFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TextviewFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/fa-regular-400.ttf");
            setTypeface(tf);
        }
    }
}
