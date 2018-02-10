package org.netzd.bottomnavigatordip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno12 on 10/02/18.
 */

//Saber que elemento mostrar de acuerdo a la pestaña

public class MusicAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = null;

    public MusicAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
