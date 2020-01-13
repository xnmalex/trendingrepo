package com.gojek.assignment.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gojek.assignment.R
import com.gojek.assignment.databinding.MainFragmentBinding
import com.gojek.assignment.injection.ViewModelFactory
import javax.inject.Inject

class TrendingRepoFragment : Fragment() {

    companion object {
        fun newInstance() = TrendingRepoFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: TrendingRepoListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrendingRepoListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage:Int){
//
    }

    private fun hideError(){
    }

}
