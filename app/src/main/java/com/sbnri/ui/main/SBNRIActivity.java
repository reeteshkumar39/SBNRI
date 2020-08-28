package com.sbnri.ui.main;

import android.os.Bundle;

import com.sbnri.R;
import com.sbnri.base.BaseActivity;
import com.sbnri.ui.list.ListFragment;

public class SBNRIActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_sbnri;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();    }
}