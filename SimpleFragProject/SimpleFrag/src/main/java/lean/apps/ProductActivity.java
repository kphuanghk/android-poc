package lean.apps;

import android.content.Context;
import android.graphics.*;
import android.os.*;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.net.*;

public class ProductActivity extends Activity {

    private static final String TAG = ProductActivity.class.getSimpleName();
    private ImageView imageView;
    private static String myimage = "http://2.bp.blogspot.com/-rPTukcLUt40/UXs_KOF2EbI/AAAAAAAAHWk/CKBJrq2V9tE/s200/silver-apple-logo-apple-picture.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Context c = getParent() != null ? getParent() : this;
        AsyncClass async;
        async = new AsyncClass(c);
        async.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

    class AsyncClass extends AsyncTask<String, Integer, Bitmap> {

        private Context context;
        private ProgressDialog progressDlg;

        public AsyncClass(Context c) {
            this.context = c;
        }

        protected Bitmap doInBackground(String... params) {
            try {
                Bitmap bitmap = null;
                URL url = new URL("http://www.antisocialmediallc.com/wp-content/uploads/2012/02/Right-Arrow-Icon.jpg");
                URLConnection urlConnection = url.openConnection();
                Log.i(TAG, "Opened");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Log.i(TAG, "Buffered");
                try {
                    Log.i(TAG, "Before In BitMap Fac");
                    bitmap = BitmapFactory.decodeStream(in);
                    return bitmap;
                } finally {
                    Log.i(TAG, "Close stream..");
                    in.close();
                }
            } catch (UnknownServiceException e) {
                Log.i(TAG, e.getMessage());
            } catch (MalformedURLException e) {
                Log.i(TAG, e.getMessage());
            } catch (IOException e) {
                Log.i(TAG, e.getMessage());
            } catch (Exception e) {
                Log.i(TAG, e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            Log.i(TAG, "Done in background, post execute...");
            imageView = (ImageView) findViewById(R.id.imageView);
            Log.i(TAG, "Trying to update image view");
            imageView.setImageBitmap(result);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            progressDlg.dismiss();
            //Do some work progressDlg.cancel();
        }

        protected void onPreExecute() {
            progressDlg = new ProgressDialog(context);
            progressDlg.setMessage("ing...");
            progressDlg.setMax(100);
            progressDlg.setCancelable(true);
            progressDlg.show();
        }
    }
}
