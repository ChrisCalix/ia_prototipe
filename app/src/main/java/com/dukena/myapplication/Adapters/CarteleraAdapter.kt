package com.dukena.myapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dukena.myapplication.R
import com.dukena.myapplication.databinding.MoviewViewBinding
import com.dukena.myapplication.models.response.CarteleraResponse
import com.dukena.myapplication.models.response.CinemaResponse
import com.dukena.myapplication.models.response.Movies

class CarteleraAdapter(var movies: List<Movies>, val listener: CarteleraInterface) :
    RecyclerView.Adapter<CarteleraAdapter.ViewHolder>() {

    interface CarteleraInterface{
        fun getMovieSelected( movie: Movies)
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
        fun bind(movie: Movies) = with(mBinding) {
            //  mBinding.ivMovie.setImageURI(Uri(movie.uris))
            mBinding.tvMovie.text = movie.name
        }

    }
}