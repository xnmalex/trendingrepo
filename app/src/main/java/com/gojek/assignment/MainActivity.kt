package com.gojek.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gojek.assignment.ui.main.TrendingRepoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingRepoFragment.newInstance())
                .commitNow()
        }
    }

}
