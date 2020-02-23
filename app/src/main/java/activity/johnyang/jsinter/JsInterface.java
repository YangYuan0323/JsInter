package activity.johnyang.jsinter;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * @author JohnYang
 * @description:
 * @date :2020-02-09 14:24
 */
public class JsInterface {
    private String TAG = "JsInterface-->";
    private JsBridge jsBridge;

    public JsInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    @JavascriptInterface
    public void setValue(String value) {
        Log.i(TAG, "value:" + value);
        jsBridge.setTextviewValue(value);
    }
}
