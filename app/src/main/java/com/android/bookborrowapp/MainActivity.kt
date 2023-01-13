package com.android.bookborrowapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.bookborrowapp.auth.LoginActivity
import com.nd.bookborrowapp.utils.ConnectivityLiveData

class MainActivity : AppCompatActivity() {

    lateinit var connectivityLiveData: ConnectivityLiveData

    lateinit var tvError: TextView
    lateinit var tvRetry:TextView
    lateinit var avLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(this)
        setContentView(R.layout.activity_main)
        initializeWidgets()
        if (isOnline(this)){
            connectivityLiveData.observe(this, Observer { isConnected ->

                Log.d("TAG_NotConnected", "onCreate:$isConnected ")

                if (isConnected){
                    val handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 4000) // Delay for 4 seconds (4000 milliseconds)

                }else{
                    Toast.makeText(this,"اشکال در شبکه",Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            avLoading.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvRetry.visibility = View.VISIBLE
            tvRetry.setOnClickListener {
                avLoading.visibility = View.VISIBLE
                tvError.visibility = View.GONE
                tvRetry.visibility = View.GONE
                if (isOnline(this)){
                    connectivityLiveData.observe(this, Observer { isConnected ->

                        Log.d("TAG_NotConnected", "onCreate:$isConnected ")

                        if (isConnected){
                            val handler = Handler()
                            handler.postDelayed({
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }, 4000) // Delay for 4 seconds (4000 milliseconds)

                        }else{
                            Toast.makeText(this,"اشکال در شبکه",Toast.LENGTH_SHORT).show()
                        }
                    })
                }else{
                    Toast.makeText(this,"لطفا از اتصال اینترنت خود اطمینان حاصل کنید", Toast.LENGTH_SHORT).show()
                    avLoading.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvRetry.visibility = View.VISIBLE
                }
            }
        }


    }


    private fun initializeWidgets() {
        tvError = findViewById(R.id.tv_error)
        tvRetry = findViewById(R.id.tv_retry)
        avLoading = findViewById(R.id.av_loading)
    }
}

// Check internet connection
@SuppressLint("ServiceCast")
private fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {

            return true
        }
    }
    return false
}


