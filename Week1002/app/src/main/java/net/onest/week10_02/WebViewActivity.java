package net.onest.week10_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.webview);
//        webView.loadUrl("http://m.kuaidi100.com/");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<head>");
        stringBuffer.append("<title>网页标题</title>");
        stringBuffer.append("</head>");
        stringBuffer.append("<body>网页内容</body>");
        stringBuffer.append("</html>");

        webView.loadDataWithBaseURL(null,stringBuffer.toString(),
                "text/html","utf-8",null);
//        webView.loadData(str,
//                "text/html","utf-8");
    }
}
