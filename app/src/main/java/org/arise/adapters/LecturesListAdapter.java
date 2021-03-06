package org.arise.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import arise.arise.org.arise.CourseDetailsActivity;
import arise.arise.org.arise.R;

/**
 * Created by arpit on 15/3/15.
 */
public class LecturesListAdapter extends BaseAdapter{

    private JSONArray lectures;
    private Context context;
    private boolean completed;
    private boolean current;

    public LecturesListAdapter(JSONArray lectureArray, Context context, Boolean completed, Boolean current) {
        this.lectures = lectureArray;
        this.context = context;
        this.completed = completed;
        this.current = current;

    }

    @Override
    public int getCount() {
        return lectures.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        JSONObject lecture = null;
        String lectureName = "";
        boolean lectureComplete = false;

        try {
            lecture = lectures.getJSONObject(i);
            lectureName = lecture.getString("name");

            if(current)
            {
                lectureComplete = lecture.getBoolean("completed");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(view == null) {
            if(completed || current) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.lectures_list_item_indicator, null);
            }
            else
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.lectures_list_item, null);
            }
        }
        else
        {
            row = view;
        }

        TextView title = (TextView) row.findViewById(R.id.lecture_title);
        title.setText(lectureName);

        if(current)
        {
            ImageView toFill = (ImageView) row.findViewById(R.id.lecture_status);

            if(lectureComplete)
            {
                toFill.setBackgroundColor(Color.parseColor("#b2ff59"));
            }
            else if(!lectureComplete)
            {
                toFill.setBackgroundColor(Color.parseColor("#ff5252"));
            }
        }
        else if(completed)
        {
            ImageView toFill = (ImageView) row.findViewById(R.id.lecture_status);
            toFill.setBackgroundColor(Color.parseColor("#b2ff59"));
        }
        return row;
    }
}
