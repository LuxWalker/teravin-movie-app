package alangsatinantongga.kotlin.teravinmovieapp.ui

import alangsatinantongga.kotlin.teravinmovieapp.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnlineListActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}