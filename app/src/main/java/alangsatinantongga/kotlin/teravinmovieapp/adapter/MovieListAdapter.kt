package alangsatinantongga.kotlin.teravinmovieapp.adapter

import alangsatinantongga.kotlin.teravinmovieapp.databinding.ItemMoviesBinding
import alangsatinantongga.kotlin.teravinmovieapp.model.MovieListResponse
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class MovieListAdapter @Inject constructor(): RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    private lateinit var binding: ItemMoviesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMoviesBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = 10

    override fun getItemViewType(position: Int): Int = position
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: MovieListResponse.ResultsItem) {
            binding.apply {
                tvTitle.text = item.title
                tvDate.text = item.releaseDate
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }

        }
    }

    private var onItemClickListener: ((MovieListResponse.ResultsItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieListResponse.ResultsItem) -> Unit) {
        onItemClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<MovieListResponse.ResultsItem>() {
        override fun areItemsTheSame(oldItem: MovieListResponse.ResultsItem, newItem: MovieListResponse.ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieListResponse.ResultsItem, newItem: MovieListResponse.ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}