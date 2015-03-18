package arise.arise.org.arise;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.arise.enums.Options;
import org.arise.interfaces.IAsyncInterface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Arpit Phanda on 2/24/2015.
 */
public class AsyncTaskManager extends AsyncTask< List<NameValuePair>,Void,Void> {

    private Context context;
    private Options option;
    private String responseStr;
    private IAsyncInterface activity;
    private ProgressDialog progDailog;

    public AsyncTaskManager(Options option, IAsyncInterface activity)
    {
        super();
        this.option = option;
        this.context = (Activity)activity;
        this.activity = activity;
    }

    public AsyncTaskManager(Options option, IAsyncInterface activity, Context context)
    {
        super();
        this.option = option;
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected Void doInBackground(List<NameValuePair>...params) {
        if(option == Options.SIGNUP || option==Options.LOGIN ||option==Options.ALL_COURSES || option==Options.CURRENT_COURSES || option == Options.COMPLETED_COURSES
                ||option==Options.LOGOUT || option==Options.UPDATE_DETAILS || option==Options.UPDATE_PASSWORD || option==Options.NEW_COURSE ||option ==Options.NEW_COURSE_LECTURE_COMPLETE || option==Options.CURRENT_LECTURE_COMPLETE)
        {
            try {
                sendToDB(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void sendToDB(List<NameValuePair> param) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(param.get(param.size()-1).getValue());

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param.subList(0,param.size()-1)));
        } catch (UnsupportedEncodingException e) {
            System.out.print("Fucked");
        }

        HttpResponse response = httpClient.execute(httpPost, LoginActivity.localContext);
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
        if(option!=Options.NEW_COURSE && option != Options.NEW_COURSE_LECTURE_COMPLETE && option!=Options.CURRENT_LECTURE_COMPLETE)
        {
            progDailog.dismiss();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(option!=Options.NEW_COURSE && option !=Options.NEW_COURSE_LECTURE_COMPLETE && option!=Options.CURRENT_LECTURE_COMPLETE)
        {
            progDailog = new ProgressDialog(context);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.show();
        }

    }
}
