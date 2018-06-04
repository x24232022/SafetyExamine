package com.avicsafety.ShenYangTowerComService.Fragement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avicsafety.lib.CustomView.AvicLoadingDialog;
import com.avicsafety.lib.tools.L;

import org.xutils.x;

public class BaseFragment extends Fragment {
    private boolean injected = false;
    protected Context context;
    protected Activity oThis;
    private View rootView;
    private boolean isShow = false;
    protected AvicLoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.TAG = this.getClass().getName();
        injected = true;
        context = getContext();
        oThis = getActivity();
        if (rootView == null)
        {
            rootView =  x.view().inject(this, inflater, container);
            isShow = false;
        }else{
            isShow = true;
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        if (!isShow)
        {
            InitializeComponent();
            InitializeData();
            InitializeEvent();
        }
    }

    protected void InitializeComponent() {
        loadingDialog = new AvicLoadingDialog(oThis);
        // TODO Auto-generated method stub
    }
    protected void InitializeData() {
        // TODO Auto-generated method stub
    }

    protected void InitializeEvent() {
        // TODO Auto-generated method stub
    }

}
