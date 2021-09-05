package op.hachm1.travelapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


class SplashActivity : AppCompatActivity() {

    private lateinit var startButton: MaterialButton
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //show agree button
        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener{
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        //show user privacy policy
        webView = findViewById(R.id.webview)
        //privacy policy made using https://app-privacy-policy-generator.firebaseapp.com
        webView.loadUrl("file:///android_asset/privacypolicy.html")

    }
}
