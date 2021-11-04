package com.dukena.myapplication.fragments.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.dukena.myapplication.Adapters.MoviesAdapter
import com.dukena.myapplication.R
import com.dukena.myapplication.activities.Home.HomeActivity
import com.dukena.myapplication.databinding.FragmentMoviesBinding
import com.dukena.myapplication.fragments.detail_movie.view.DetailMovieFragment
import com.dukena.myapplication.fragments.movies.viewmodel.MovieViewModel
import com.dukena.myapplication.models.response.CinemaResponse


class MoviesFragment : Fragment(), MoviesAdapter.MovieInterface{

    private lateinit var mBinding: FragmentMoviesBinding
    private lateinit var mViewModel: MovieViewModel
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMoviesBinding.inflate(layoutInflater,container,false)
        mViewModel = ViewModelProvider(this).get()

        with(mBinding){
            movieViewModel = mViewModel
            lifecycleOwner = this@MoviesFragment

            mViewModel.getCinemas()

            with(mViewModel) {
                /*progressBar.observe(viewLifecycleOwner, { showProgresBar ->
                    with(activity as HomeActivity) {
                        if (showProgresBar) {
                            InstanceLoadingDialog("Cargando")
                        } else {
                            hideDialog()
                        }
                    }
                })*/
                response.observe(viewLifecycleOwner,{
                    response ->
                        rvMovies.adapter = MoviesAdapter(response, this@MoviesFragment)
                })

                message.observe(viewLifecycleOwner, { message ->
                    with(activity as HomeActivity) {
                        InstanceDialog(message, "No se pudo recuperar los datos", "Salir")
                    }
                })
            }
        }

        /*mBinding.btnMovie.setOnClickListener {
            mFragmentManager = activity?.supportFragmentManager!!
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frame_cartelera, DetailMovieFragment(), DetailMovieFragment::class.java.name)
                .addToBackStack(DetailMovieFragment::class.java.name)
                .commit()
        }*/

        // Inflate the layout for this fragment
        return mBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun getMovieSelected(movie: CinemaResponse) {
        Toast.makeText(context,movie.name, Toast.LENGTH_LONG).show()
    }
}