package arise.arise.org.arise;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.arise.adapters.TabsPagerAdapter;
import org.arise.fragments.NavigationDrawer;
import org.arise.tab.SlidingTabLayout;


//public class HomeActivity extends ActionBarActivity {

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabs.setCustomTabView(R.layout.custom_tab, R.id.tab_text);
        tabs.setDistributeEvenly(true);

        tabs.setBackgroundColor(getResources().getColor(R.color.button_material_dark));
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.material_blue_grey_800));
        tabs.setViewPager(viewPager);

        NavigationDrawer drawer = (NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawer.setUpDrawer(drawerLayout,toolbar, R.id.fragment_navigation_drawer);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
