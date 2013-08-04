package lean.apps;

import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
        implements Button.OnClickListener, View.OnTouchListener, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String TAG = "MainActivity";

    private static GestureDetector gsDector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Create Gesture Detector */
        gsDector = new GestureDetector(this, this);


        Button b = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.touch_text_view);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

        b.setOnClickListener(this);
        tv.setOnTouchListener(this);
        tv.setFocusable(true);
        tv.setClickable(true);
        tv.setLongClickable(true);
        gsDector.setIsLongpressEnabled(true);

        new Thread(new Runnable() {
            public void run() {
                while (pb.getProgress() < 100) {
                    //mProgressStatus = doWork();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Log.i(TAG, e.getMessage());
                    }
                    pb.setProgress(pb.getProgress() + 5);
                }
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "Button is clicked.");
        Fragment myList = getFragmentManager().findFragmentByTag("MyList");

        if (myList == null) {
            Log.i(TAG, "My List is null");
            Fragment simpleFrag = new SimpleFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, simpleFrag, "MyList").commit();
            Log.i(TAG, "Get Fragment Manager and Commit");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Log.i(TAG, "View " + v.toString() + ", event = " + event.toString());
        //Toast.makeText(this, "View " + v.toString() + ", event = " + event.toString(), Toast.LENGTH_LONG).show();
        return gsDector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG, "OnSingleTapConfirmed, Event = " + e.toString());
        Toast.makeText(this, "OnSingleTapConfirmed, Event = " + e.toString(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "OnDoubleTapConfirmed, Event = " + e.toString());
        Toast.makeText(this, "OnDoubleTapConfirmed, Event = " + e.toString(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "OnLongPress, Event = " + e.toString());
        Toast.makeText(this, "OnLongPress, Event = " + e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG, "OnFling, vX = " + velocityX + " , vY = " + velocityY);
        Log.i(TAG, "OnFling, e1 " + e1.toString());
        Log.i(TAG, "OnFling, e2 " + e2.toString());
        Toast.makeText(this, "OnFling, e1 = " + e1.toString() + ", e2 = " + e2.toString(), Toast.LENGTH_LONG).show();
        return false;
    }
}
