package `in`.iot.lab.healthyways.loginSignup

import `in`.iot.lab.healthyways.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun openLoginIntent(view: View) {

        val intent = Intent(this,LoginSignupActivity::class.java)
        startActivity(intent)

    }
}