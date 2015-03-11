package org.arise.tabs.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.arise.enums.Options;
import org.arise.interfaces.IAsyncInterface;

import arise.arise.org.arise.AsyncTaskManager;
import arise.arise.org.arise.R;

/**
 * Created by Arpit Phanda on 3/5/2015.
 */
public class AllCoursesDetail extends Fragment implements IAsyncInterface{
    static int count = 0;
    public AllCoursesDetail()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment,container,false);
        ListView list = (ListView) layout.findViewById(R.id.list_course_lectures);

        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        new AsyncTaskManager(Options.GET_ALL_COURSES,this).execute();

        super.onCreate(savedInstanceState);

    }

    @Override
    public void parseJSON(String jsonResponse) {

    }
}
