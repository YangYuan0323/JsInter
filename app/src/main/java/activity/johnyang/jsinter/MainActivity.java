package activity.johnyang.jsinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements JsBridge {
    TextView mTVResult;
    WebView mWebView;
    private Handler handler;
    private EditText sendText;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets(savedInstanceState);
    }

    private void initWidgets(Bundle savedInstanceState) {

        mWebView = findViewById(R.id.webView);
        mTVResult = findViewById(R.id.tv_result);
        sendText = findViewById(R.id.sendText);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = sendText.getText().toString().trim();
                mWebView.loadUrl("javascript:if(window.remote){window.remote('"+content+"')}");
            }
        });

        handler = new Handler();

        //允许webview加载js代码
        mWebView.getSettings().setJavaScriptEnabled(true);
        //给webview添加js接口
        mWebView.addJavascriptInterface(new JsInterface(MainActivity.this),"JsLauncher");
//        mWebView.loadUrl("http://192.168.88.102:8080/JsInterWeb/testhtml.html");
        mWebView.loadUrl("file:///android_asset/testhtml.html");
    }

    @Override
    public void setTextviewValue(final String value) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mTVResult.setText(value);
            }
        });
    }
}
