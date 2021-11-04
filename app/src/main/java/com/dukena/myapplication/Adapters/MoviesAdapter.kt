package com.dukena.myapplication.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dukena.myapplication.R
import com.dukena.myapplication.databinding.MoviewViewBinding
import com.dukena.myapplication.models.response.CinemaResponse
import kotlin.properties.Delegates

class MoviesAdapter(var movies: List<CinemaResponse>, val listener: MovieInterface) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    interface MovieInterface{
        fun getMovieSelected( movie: CinemaResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.moview_view, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listener.getMovieSelected(movie)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mBinding = MoviewViewBinding.bind(view)
        fun bind(movie: CinemaResponse) = with(mBinding) {
          //  mBinding.ivMovie.setImageURI(Uri(movie.uris))
            mBinding.tvMovie.text = movie.name
        }

    }
}