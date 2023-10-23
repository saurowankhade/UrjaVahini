package com.example.electricalmaterial.add_material;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.electricalmaterial.done.animation.DoneAnimation;

public class AddMaterialFormPagerAdapter extends FragmentPagerAdapter {
    public AddMaterialFormPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Log.d("Position Number : ",position+" is a position");
        if (position == 0){
            return new BacisInfromation();
        } else if (position==1) {
            return new SiteInformation();
        } else if (position==2){
            return new MaterialInfromation();
        } else if (position == 3) {
            return new EndInfromation();
        } else if (position==4) {
            return new DoneAnimation();
        } else {
           return null;
        }

    }

    @Override
    public int getCount() {
        return 5; // Number of form steps
    }
}
