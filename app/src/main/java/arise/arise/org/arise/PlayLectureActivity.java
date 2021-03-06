package arise.arise.org.arise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.arise.enums.Options;
import org.arise.enums.SharedPreferenceEnum;
import org.arise.fragments.NavigationDrawer;
import org.arise.interfaces.IAsyncInterface;

import java.util.ArrayList;
import java.util.List;


public class PlayLectureActivity extends BaseActivity implements YouTubePlayer.OnInitializedListener,IAsyncInterface{

    public static final String API_KEY = "AIzaSyC64dnPxlBecVxK2yHzZ4y5liGdrwNMBSE";
    private YouTubePlayer youTubePlayer;
    private String url;

    private final String httpurl = "http://ariseimpactapps.in/audiolearningapp/add_upgrade_lectures.php";
    int courseID;
    int lectureID;
    String lectureName;
    boolean course_completed = false;
    boolean course_current = false;
    boolean lectureComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_lecture);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Bundle bundle = getIntent().getExtras();
        lectureName = bundle.getString("name");
        courseID = bundle.getInt("courseID");
        lectureID = bundle.getInt("lectureID");
        course_completed = bundle.getBoolean("course_completed");
        course_current = bundle.getBoolean("course_current");

        if(course_current)
        {
            lectureComplete = bundle.getBoolean("lecture_completed");
        }

        url = bundle.getString("url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(lectureName);

        NavigationDrawer drawer = (NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawer.setUpDrawer(drawerLayout,toolbar, R.id.fragment_navigation_drawer);

        YouTubePlayerSupportFragment youTubePlayerFragment = (YouTubePlayerSupportFragment) this.getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(API_KEY,this );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play_lecture, menu);
        return true;
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        Toast.makeText(this, errorReason.toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        youTubePlayer = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);
        if (!wasRestored) {
            youTubePlayer.cueVideo(url);
        }
    }


    YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("courseID", courseID+""));
            nameValuePairs.add(new BasicNameValuePair("lectureID", lectureID+""));

           if(course_current)
           {
               if(!lectureComplete)
               {
                   nameValuePairs.add(new BasicNameValuePair("type","lecture_complete"));
                   nameValuePairs.add(new BasicNameValuePair("url", httpurl));
                   new AsyncTaskManager(Options.CURRENT_LECTURE_COMPLETE, PlayLectureActivity.this).execute(nameValuePairs);
               }
           }
           else if(!course_current && !course_completed )
           {
               nameValuePairs.add(new BasicNameValuePair("type","new_lecture_complete"));
               nameValuePairs.add(new BasicNameValuePair("url", httpurl));
               new AsyncTaskManager(Options.NEW_COURSE_LECTURE_COMPLETE, PlayLectureActivity.this).execute(nameValuePairs);
           }
        }

        @Override
        public void onVideoStarted() {
            if(!course_current && !course_completed)
            {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("courseID", courseID+""));
                nameValuePairs.add(new BasicNameValuePair("type","move_to_current"));
                nameValuePairs.add(new BasicNameValuePair("url", httpurl));
                new AsyncTaskManager(Options.NEW_COURSE, PlayLectureActivity.this).execute(nameValuePairs);
            }
        }
    };

    @Override
    public void parseJSON(String jsonResponse) {

    }

    @Override
    public void onBackPressed() {
        Intent homeActivity = new Intent(this,HomeActivity.class);
        this.startActivity(homeActivity);

    }
}
