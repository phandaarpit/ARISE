package org.arise.tabs.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arise.arise.org.arise.R;

/**
 * Created by Arpit Phanda on 3/5/2015.
 */
public class AllCoursesDetail extends Fragment {

    public AllCoursesDetail()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment,container,false);
        return layout;
    }
}
