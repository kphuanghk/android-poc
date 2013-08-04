package lean.apps;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kphuanghk on 8/4/13.
 */
public class SimpleFragment extends Fragment {
    private static final String TAG = "SimpleFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate is called.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.simple_fragment, null);
        Log.i(TAG, "Done onCreateView");
        return v;
      //  return super.onCreateView(inflater, container, savedInstanceState);
    }
}
