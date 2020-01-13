package com.gojek.assignment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gojek.assignment.R
import com.gojek.assignment.data.Repositories
import com.gojek.assignment.databinding.ItemTrendingrepoBinding;

class TrendingRepoListAdapter: RecyclerView.Adapter<TrendingRepoListAdapter.ViewHolder>() {
    private lateinit var repoList:List<Repositories>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTrendingrepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_trendingrepo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {
        return if(::repoList.isInitialized) repoList.size else 0
    }

    fun updateRepoList(repoList:List<Repositories>){
        this.repoList = repoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemTrendingrepoBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = TrendingRepoItemViewModel()
        fun bind(repo:Repositories){
            viewModel.bind(repo)
            binding.viewModel = viewModel
        }
    }
}