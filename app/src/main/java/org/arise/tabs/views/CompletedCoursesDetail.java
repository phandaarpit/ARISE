package org.arise.tabs.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.arise.adapters.CoursesListAdapter;
import org.arise.enums.CourseRequestType;
import org.arise.enums.CourseStatus;
import org.arise.enums.Options;
import org.arise.interfaces.IAsyncInterface;
import org.arise.listeners.CourseListListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arise.arise.org.arise.AsyncTaskManager;
import arise.arise.org.arise.R;

/**
 * Created by Arpit Phanda on 3/5/2015.
 */
public class CompletedCoursesDetail extends Fragment implements IAsyncInterface{
    private final String url ="http://ariseimpactapps.in/audiolearningapp/course_details.php";
    JSONArray courses;
    private View layout;
    private ListView list;

    public CompletedCoursesDetail()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment,container,false);
        list = (ListView) layout.findViewById(R.id.list_course_lectures);

        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair(CourseRequestType.TYPE.toString(), CourseRequestType.COMPLETED.toString()));
        nameValuePairs.add(new BasicNameValuePair("url", url));
        new AsyncTaskManager(Options.COMPLETED_COURSES,this, getActivity()).execute(nameValuePairs);

        super.onCreate(savedInstanceState);

    }

    @Override
    public void parseJSON(String jsonResponse) {
        JSONObject completeResponse;
        JSONArray courses;
        boolean status;

        try {
            completeResponse = new JSONObject(jsonResponse);
            status = completeResponse.getBoolean("success");
            if(status)
            {
                courses = completeResponse.getJSONArray("courses");
                this.courses = courses;
                if(courses == null)
                {
                    Log.d("CHECK","ITS NULL");
                }
                Log.d("CHECK", "NOT NULL" + courses.toString());
                list.setAdapter(new CoursesListAdapter(getActivity(),this.courses, CourseStatus.COMPLETED));
                list.setOnItemClickListener(new CourseListListener(getActivity(),courses,CourseStatus.COMPLETED));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
