package arise.arise.org.arise;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.arise.enums.Options;
import org.arise.interfaces.IAsyncInterface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Arpit Phanda on 2/24/2015.
 */
public class AsyncTaskManager extends AsyncTask< List<NameValuePair>,Void,Void> {

    private Options option;
    private String responseStr;
    private IAsyncInterface activity;
    private ProgressDialog progDailog;

    public AsyncTaskManager(Options option, IAsyncInterface activity)
    {
        super();
        this.option = option;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(List<NameValuePair>...params) {
        if(option == Options.SIGNUP)
        {
            try {
                sendToDB(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(option==Options.LOGIN)
        {
            try {
                signin(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(option==Options.GET_ALL_COURSES)
        {
            try {
                getCourses(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void getCourses(List<NameValuePair> param) {

    }

    private void signin(List<NameValuePair> param) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(param.get(param.size()-1).getValue());

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param.subList(0,param.size()-1)));
        } catch (UnsupportedEncodingException e) {
            System.out.print("Fucked");
        }

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            responseStr = EntityUtils.toString(resEntity).trim();
            Log.d("SignIn",responseStr);
        }
    }

    private void sendToDB(List<NameValuePair> param) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(param.get(param.size()-1).getValue());

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param.subList(0,param.size()-1)));
        } catch (UnsupportedEncodingException e) {
            System.out.print("Fucked");
        }

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();

        if (resEntity != null) {
            responseStr = EntityUtils.toString(resEntity).trim();
            Log.d("Register",responseStr);
         }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.parseJSON(responseStr);
        progDailog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDailog = new ProgressDialog((Activity)activity);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.show();
    }
}
