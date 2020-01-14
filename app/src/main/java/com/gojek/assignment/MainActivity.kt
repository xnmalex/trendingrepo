package com.gojek.assignment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gojek.assignment.databinding.MainActivityBinding
import com.gojek.assignment.injection.ViewModelFactory
import com.gojek.assignment.ui.main.TrendingRepoListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel:TrendingRepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.separator)!!)
        binding.repoListView.addItemDecoration(decorator)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(TrendingRepoListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        binding.tryAgainBtn.setOnClickListener{
            viewModel.loadTrendingRepos()
        }

        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, TrendingRepoFragment.newInstance())
//                .commitNow()
        }

    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager
                .EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }

    private fun disconnected() {
        binding.repoListView.visibility = View.INVISIBLE
        binding.noInternet.visibility = View.VISIBLE
    }

    private fun connected() {
        binding.repoListView.visibility = View.VISIBLE
        binding.noInternet.visibility = View.INVISIBLE
        //fetchFeeds()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun showError(@StringRes errorMessage:Int){
//        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
//        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
//        errorSnackbar?.show()
        error(getString(errorMessage))
    }

    private fun hideError(){
        //errorSnackbar?.dismiss()
    }

}
