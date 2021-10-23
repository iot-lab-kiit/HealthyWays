package `in`.iot.lab.healthyways.loginSignup

import `in`.iot.lab.healthyways.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import kotlinx.android.synthetic.main.activity_login_signup.*

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        val ccp: CountryCodePicker = findViewById(R.id.ccp)

        ccp.registerPhoneNumberTextView(edtPhone)





    }
}