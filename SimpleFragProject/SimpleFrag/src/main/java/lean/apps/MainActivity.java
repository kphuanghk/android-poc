package lean.apps;

import android.os.*;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.Random;

/**
 *
 */
public class MainActivity extends Activity
        implements Button.OnClickListener, View.OnTouchListener, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String TAG = "MainActivity";

    private static GestureDetector gsDector;

    private static Random random = new Random();

    private boolean mLongPressed; //default false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Create Gesture Detector */
        gsDector = new GestureDetector(this, this);


        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);

        TextView tv = (TextView) findViewById(R.id.touch_text_view);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
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
        switch(v.getId()) {
            case R.id.button:
                addSimpleFragment();
                break;
            case R.id.button2:
                showAnimation();
                break;
            default:
                ;
        }
    }

    private void showAnimation(){
        Fragment f = getFragmentManager().findFragmentByTag("MyList");
        Log.i(TAG, "Animation is executing..." + f);

        if(f != null){
            float y = 600* random.nextFloat();
            float x = 600 * random.nextFloat();
            f.getView().animate()
                    .translationY(y)
                    .translationX(x)
                    .alpha(random.nextFloat())
                    .setDuration(2000)
                    .withLayer();
            Log.i(TAG, "Translate (x, y) = (" + x + "," + y + ") , view name =" + f.getView());
        }
    }
    private void addSimpleFragment(){
        Log.i(TAG, "Button is clicked.");
        Fragment myList = getFragmentManager().findFragmentByTag("MyList");

        if (myList == null) {
            Log.i(TAG, "My List is null");
            Fragment simpleFrag = new SimpleFragment();
            getFragmentManager().beginTransaction().add(R.id.main_root_relative_layout, simpleFrag, "MyList").commit();
            Log.i(TAG, "Get Fragment Manager and Commit");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Log.i(TAG, "View " + v.toString() + ", event = " + event.toString());
        //Toast.makeText(this, "View " + v.toString() + ", event = " + event.toString(), Toast.LENGTH_LONG).show();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mLongPressed = false;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "Long Pressed = " + mLongPressed + ", Y =  " + event.getY());
            default:
                ;
        }
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
        mLongPressed = true;
        Toast.makeText(this, "OnLongPress, Event = " + e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG, "OnFling, vX = " + velocityX + " , vY = " + velocityY);
        Log.i(TAG, "OnFling, e1 " + e1.toString());
        Log.i(TAG, "OnFling, e2 " + e2.toString());

        Fragment f = getFragmentManager().findFragmentByTag("MyList");
        Log.i(TAG, "Animation is executing..." + f);

        if(f != null){
            float x = e1.getX() - e2.getX();
            float y = e1.getY() - e2.getY();
            f.getView().animate().translationY(y).withLayer();
            f.getView().animate().translationX(x).withLayer();
            //f.getView().animate().alpha(random.nextFloat()).withLayer();
            Log.i(TAG, "Fling Translate (x, y) = (" + x + "," + y + ") , view name =" + f.getView());
        }

        //Toast.makeText(this, "OnFling, e1 = " + e1.toString() + ", e2 = " + e2.toString(), Toast.LENGTH_LONG).show();
        return false;
    }
}
