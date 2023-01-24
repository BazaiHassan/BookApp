package com.android.bookborrowapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.bookborrowapp.R
import com.android.bookborrowapp.auth.AuthViewModel
import com.android.bookborrowapp.auth.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    val authViewModel : AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(authViewModel.checkLoginLivedata.value == true){
            Toast.makeText(this,"token is available",Toast.LENGTH_SHORT).show()
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    override fun onResume() {
        
        super.onResume()
     authViewModel.checkLogin()
    }
}