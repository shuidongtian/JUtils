package com.letian.jutils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {


    public String TAG = getClass().getSimpleName();
    public final static int HIDE_LOADING_DIALOG = 1;
    public final static int CLEAR_CHECK = 2;
    public final static int HIDE_PRIINT = 3;
    public final static int HIDE_EXPORT = 4;


    public final static int SHOW_LOADING = 100;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).setCurrentFragment(this);


        initView();
        initData();

    }







    public abstract void initView();

    public abstract void initData();



    /**
     * fragment拦截返回键返回true，不拦截返回false
     */
    public abstract boolean onBackPressed();



    /**
     * dialog 隐藏软键盘
     *
     */

    public void hideSoftInputFromWindow(AlertDialog alertDialog){
        //点击下一步，隐藏软键盘，待验证
        View v = alertDialog.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


}
