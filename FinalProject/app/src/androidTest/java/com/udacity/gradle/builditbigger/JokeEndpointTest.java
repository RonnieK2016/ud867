package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;


/**
 * Created by angelov on 7/25/2018.
 */
public class JokeEndpointTest extends ApplicationTestCase<Application> {

    private static String TAG = JokeEndpointTest.class.getSimpleName();

    public JokeEndpointTest() {
        super(Application.class);
    }

    public void testNonEmptyJoke() {
        String resultJoke = null;
        EndpointsAsyncTask tellJokeTask = new EndpointsAsyncTask(getContext());
        tellJokeTask.execute();
        try {
            resultJoke = tellJokeTask.get();
        }
        catch (Exception exc) {
            Log.e(TAG, "Exception during getting joke", exc);
        }
        assertNotNull(resultJoke);

    }
}