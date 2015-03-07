package arise.arise.org.arise;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.arise.adapters.TabsPagerAdapter;
import org.arise.fragments.NavigationDrawer;
import org.arise.tab.SlidingTabLayout;


public class HomeActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private SlidingTabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        tabs = (SlidingTabLayout)findViewById(R.id.tabs);

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabs.setCustomTabView(R.layout.custom_tab, R.id.tab_text);
        tabs.setDistributeEvenly(true);
//                        return getResources().getColor(R.color.material_blue_grey_800);

        tabs.setBackgroundColor(getResources().getColor(R.color.button_material_dark));
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.material_blue_grey_800));
        tabs.setViewPager(viewPager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawer drawer = (NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawer.setUpDrawer((DrawerLayout)findViewById(R.id.drawer_layout),toolbar, R.id.fragment_navigation_drawer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
