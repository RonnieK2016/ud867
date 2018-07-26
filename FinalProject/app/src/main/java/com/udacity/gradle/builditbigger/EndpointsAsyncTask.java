package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.jokesandroidlibrary.TellJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by angelov on 7/25/2018.
 */

//as per step 2 getting jokes from endpoint
public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private AsyncTaskCallBack mCallBack;

    public EndpointsAsyncTask() {
    }

    public EndpointsAsyncTask(AsyncTaskCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    protected String doInBackground(String... params) {

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if(result != null && mCallBack != null) {
            mCallBack.onTaskFinished(result);
        }
    }

    public interface AsyncTaskCallBack {
        void onTaskFinished(String result);
    }
}
