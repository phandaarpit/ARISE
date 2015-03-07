package arise.arise.org.arise;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Arpit Phanda on 2/24/2015.
 */
public class AsyncTaskManager extends AsyncTask< List<NameValuePair>,Void,Void> {


    @Override
    protected Void doInBackground(List<NameValuePair>...params) {

        try {
            sendToDB(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
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

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            String responseStr = EntityUtils.toString(resEntity).trim();
            System.out.println(responseStr);
         }

    }
}
