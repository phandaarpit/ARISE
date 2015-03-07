package arise.arise.org.arise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView linkToRegistration = (TextView)findViewById(R.id.link_to_registration);

        //check if the password is stored in sharedPreferences
        //if true fetch the credentials and decrypt them
        if(credentialsPresent())
        {
            //httppost to login.php
            //create a new session
//            SharedPreferences.Editor editor = getSharedPreferences("password", 0).edit();
//            editor.putString("password", "your password");
//            editor.commit();
        }
        else
        {
            //get the current login id and password from the activity
            //check if the user exists in database
            if(userExist())
            {
                //store the new credentials in sharedPreferences

                //switch to new Home Activity

            }
            else
            {
                //user doesnt exist, ask for them to fill the credentials again
            }
        }
    }

    private boolean userExist() {
        //check if the username passed from the
        return false;
    }

    private boolean credentialsPresent() {
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoRegistrationPage(View view) {
        Intent registrationScreen = new Intent(getApplicationContext(),RegisterationActivity.class);
        startActivity(registrationScreen);
    }

    public void gotoHome(View view) {
        Toast.makeText(getApplicationContext(),"CLicked",Toast.LENGTH_LONG).show();
        Intent homeScreen = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeScreen);
    }
}
