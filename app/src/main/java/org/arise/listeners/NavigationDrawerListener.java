package org.arise.listeners;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Arpit Phanda on 3/11/2015.
 */
public class NavigationDrawerListener implements ListView.OnItemClickListener {

    private Activity context;

    public NavigationDrawerListener(Context context){
        this.context = (Activity)context;
    }
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
        setView(position);
    }

    private void setView(int position) {


    }


}
