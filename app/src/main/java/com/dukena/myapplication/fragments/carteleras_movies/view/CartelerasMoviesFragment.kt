package com.dukena.myapplication.fragments.carteleras_movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.dukena.myapplication.Adapters.CarteleraAdapter
import com.dukena.myapplication.Adapters.MoviesAdapter
import com.dukena.myapplication.R
import com.dukena.myapplication.activities.Home.HomeActivity
import com.dukena.myapplication.databinding.FragmentCartelerasMoviesBinding
import com.dukena.myapplication.databinding.FragmentMoviesBinding
import com.dukena.myapplication.fragments.cartelera.view.CarteleraFragment
import com.dukena.myapplication.fragments.carteleras_movies.viewmodel.CarteleraMoviesViewModel
import com.dukena.myapplication.models.response.Movies

class CartelerasMoviesFragment : Fragment(), CarteleraAdapter.CarteleraInterface{

    private lateinit var mBinding: FragmentCartelerasMoviesBinding
    private lateinit var mViewModel: CarteleraMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCartelerasMoviesBinding.inflate(layoutInflater,container,false)
        mViewModel = ViewModelProvider(this).get()
        with(mBinding){
            carteleraMoviewViewModel = mViewModel
            lifecycleOwner = this@CartelerasMoviesFragment

            with(mViewModel){
                getMovies()

                response.observe(viewLifecycleOwner,{
                        response ->
                    rvMovies.adapter = CarteleraAdapter(response.movies, this@CartelerasMoviesFragment)
                })

                message.observe(viewLifecycleOwner, { message ->
                    with(activity as HomeActivity) {
                        InstanceDialog(message, "No se pudo recuperar los datos", "Salir")
                    }
                })
            }
        }

        // Inflate the layout for this fragment
        return mBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = CartelerasMoviesFragment()

    }

    override fun getMovieSelected(movie: Movies) {
        Toast.makeText(context,movie.name,Toast.LENGTH_LONG).show()
    }
}