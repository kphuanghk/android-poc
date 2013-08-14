package lean.apps;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by kphuanghk on 8/4/13.
 */
public class ParentTextView extends TextView {

    private static final String TAG = "ParentTextView" ;

    public ParentTextView(Context context) {
        super(context);
    }

    public ParentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
