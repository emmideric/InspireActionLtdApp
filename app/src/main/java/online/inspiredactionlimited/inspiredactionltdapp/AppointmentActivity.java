package online.inspiredactionlimited.inspiredactionltdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.inspireapp.R;

public class AppointmentActivity extends AppCompatActivity {
    private WebView mywebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mywebview = (WebView) findViewById(R.id.webview);
        WebSettings websettings = mywebview.getSettings();
        mywebview.loadUrl("https://inspiredactionltd.online/bookings/");
        websettings.setJavaScriptEnabled(true);
    }

    public class myWebClient extends WebViewClient{
        @Override
        public void onPageStarted (WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }
       // @Override
        public boolean  shouldOverideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public  void onBackPressed(){
        if (mywebview.canGoBack()){

        }else{
            super.onBackPressed();
        }
    }
}
