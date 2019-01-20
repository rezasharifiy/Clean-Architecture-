package com.rag.khavaranmessenger.samplecleanarchitecture.app.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.baseclass.BaseActivity;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.MainFragment;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.view.viewhandler.SwitchFragment;

public class MainActivity extends BaseActivity implements SwitchFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        showFragment(MainFragment.newInstance());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(view.getId(), fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void switchFragment(Fragment fragment) {
        showFragment(fragment);
    }


}