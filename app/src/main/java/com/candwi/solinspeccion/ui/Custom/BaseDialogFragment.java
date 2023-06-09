package com.candwi.solinspeccion.ui.Custom;

import android.app.Activity;
import android.app.DialogFragment;

/**
 * Created by luisr on 07/03/2018.
 */

public abstract class BaseDialogFragment<T> extends DialogFragment {
    private T mActivityInstance;

    public final T getActivityInstance() {
        return mActivityInstance;
    }

    @Override
    public void onAttach(Activity activity) {
        mActivityInstance = (T) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityInstance = null;
    }
}
