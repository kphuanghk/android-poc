package lean.apps;

import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements Button.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
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
}
