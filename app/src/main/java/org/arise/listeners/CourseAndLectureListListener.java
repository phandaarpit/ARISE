package org.arise.listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Arpit Phanda on 3/11/2015.
 */
public class CourseAndLectureListListener implements ListView.OnItemClickListener {
    private Context context;

    public CourseAndLectureListListener(Context context)
    {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

    }
}
