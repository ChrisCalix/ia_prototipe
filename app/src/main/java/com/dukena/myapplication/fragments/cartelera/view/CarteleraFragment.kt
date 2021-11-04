package com.dukena.myapplication.fragments.cartelera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.dukena.myapplication.R
import com.dukena.myapplication.databinding.FragmentCarteleraBinding
import com.dukena.myapplication.fragments.account.view.AccountFragment
import com.dukena.myapplication.fragments.detail_movie.view.DetailMovieFragment
import com.dukena.myapplication.fragments.movies.view.MoviesFragment

class CarteleraFragment : Fragment() {

    private lateinit var mBinding: FragmentCarteleraBinding
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCarteleraBinding.inflate(inflater, container, false)

        mFragmentManager = activity?.supportFragmentManager!!
        mFragmentManager
            .beginTransaction()
            .replace(R.id.frame_cartelera, MoviesFragment(), MoviesFragment::class.java.name)
            .commit()

        return mBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CarteleraFragment()
    }
}