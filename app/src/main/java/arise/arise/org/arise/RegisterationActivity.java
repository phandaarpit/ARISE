package arise.arise.org.arise;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.arise.enums.Options;
import org.arise.interfaces.IAsyncInterface;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class RegisterationActivity extends ActionBarActivity implements IAsyncInterface{

    EditText fname,lname,email,password,dob,contact,country,qual;
    RadioButton male,female;

    final String url = "http://172.17.14.191/ARISE/registeruser.php";

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            dob.setText(sdf.format(myCalendar.getTime()));
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        dob = (EditText)findViewById(R.id.dob);
        contact = (EditText)findViewById(R.id.contact);
        country = (EditText)findViewById(R.id.country);
        qual = (EditText)findViewById(R.id.qual);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registeration, menu);
        return true;
    }

    public void setDate(View v)
    {
        new DatePickerDialog(RegisterationActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void registerNewUser(View v) throws IOException {
        String jsonResponse = "";

        String first_name = String.valueOf(fname.getText());
        String last_name = String.valueOf(lname.getText());
        String emailId = String.valueOf(email.getText());
        String pass = String.valueOf(password.getText());
        String cont = String.valueOf(contact.getText());
        String qualification = String.valueOf(qual.getText());
        String count = String.valueOf(country.getText());
        String sex = "";
        if(male.isChecked())
        {
            sex = "M";
        }
        else if(female.isChecked())
        {
            sex = "F";
        }

        String dateOfBirth = String.valueOf(dob.getText());

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(10);
        nameValuePairs.add(new BasicNameValuePair("fname", first_name));
        nameValuePairs.add(new BasicNameValuePair("lname", last_name));
        nameValuePairs.add(new BasicNameValuePair("email", emailId));
        nameValuePairs.add(new BasicNameValuePair("password", pass));
        nameValuePairs.add(new BasicNameValuePair("contact", cont));
        nameValuePairs.add(new BasicNameValuePair("qualification", qualification));
        nameValuePairs.add(new BasicNameValuePair("gender", sex));
        nameValuePairs.add(new BasicNameValuePair("dob", dateOfBirth));
        nameValuePairs.add(new BasicNameValuePair("country", count));
        nameValuePairs.add(new BasicNameValuePair("url",url));

        Toast.makeText(getApplicationContext(),""+nameValuePairs.get(nameValuePairs.size()-1).getValue(),Toast.LENGTH_SHORT).show();

        try
        {
            new AsyncTaskManager(Options.SIGNUP,this).execute(nameValuePairs);
        }
        catch(Exception e)
        {
            System.out.println("Fucked");
        }

        Toast.makeText(getApplicationContext(),jsonResponse,Toast.LENGTH_LONG).show();
    }

    @Override
    public void parseJSON(String jsonResponse) {
        Log.d("Inside Activity","Response: "+jsonResponse);
    }
}
