package com.android.bookborrowapp.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.bookborrowapp.R
import com.android.bookborrowapp.verify.VerifyActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    val authViewModel : AuthViewModel by viewModel()
    lateinit var btn_Login : MaterialButton
     lateinit var ed_Login : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeWidgets()

        btn_Login.setOnClickListener {
            authViewModel.checkRegister(ed_Login.text.toString())
        }

        authViewModel.registerCheckLiveData.observe(this) {
            if (it.message.equals("register")) {
                  startActivity(Intent(this,VerifyActivity::class.java)
                      .apply {
                          putExtra("phone",ed_Login.text.toString())
                      })
                finish()
            }else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initializeWidgets() {
        btn_Login = findViewById(R.id.btn_Login)
        ed_Login = findViewById(R.id.ed_Login)
    }
}