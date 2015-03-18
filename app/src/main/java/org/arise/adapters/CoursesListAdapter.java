package org.arise.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.arise.enums.CourseStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import arise.arise.org.arise.R;

/**
 * Created by Arpit Phanda on 3/11/2015.
 */
public class CoursesListAdapter extends BaseAdapter{

    private Context context;
    private JSONArray arrayJSON;
    private String item;
    private String itemDesc;
    private CourseStatus option;

    public CoursesListAdapter(Context context, JSONArray courses, CourseStatus status) {
        arrayJSON = courses;
        this.context = context;
        option = status;
    }

    @Override
    public int getCount() {
        return arrayJSON.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        JSONObject course;
        item = "";
        itemDesc = "";
        boolean completedBool = false;
        boolean currentBool = false;

        try {
            course = (JSONObject) arrayJSON.get(position);
            item = course.getString("course_name");
            itemDesc = course.getString("course_description");

            if(option == CourseStatus.ALL)
            {
                completedBool = course.getBoolean("completed");
                currentBool = course.getBoolean("current");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.course_list_item_all_courses, null);
        }
        else
        {
            row = convertView;
        }

        TextView courseHeading = (TextView) row.findViewById(R.id.course_title);
        courseHeading.setText(item);
        TextView courseDescription = (TextView) row.findViewById(R.id.course_description);
        courseDescription.setText(itemDesc);

        ImageView toFill = (ImageView) row.findViewById(R.id.course_status);
        if(option == CourseStatus.ALL)
        {
            if(!currentBool && !completedBool)
            {
                toFill.setBackgroundColor(Color.parseColor("#ff5252"));
            }
            else if(currentBool && !completedBool)
            {
                toFill.setBackgroundColor(Color.parseColor("#ffb300"));
            }
            else if(!currentBool && completedBool)
            {
                toFill.setBackgroundColor(Color.parseColor("#b2ff59"));
            }
        }
        else if(option==CourseStatus.COMPLETED)
        {
            toFill.setBackgroundColor(Color.parseColor("#b2ff59"));
        }
        else if(option == CourseStatus.CURRENT)
        {
            toFill.setBackgroundColor(Color.parseColor("#ffb300"));
        }
        return row;
    }
}
