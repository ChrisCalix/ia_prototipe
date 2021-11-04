package com.dukena.myapplication.activities.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dukena.myapplication.R
import com.dukena.myapplication.databinding.ActivityHomeBinding
import com.dukena.myapplication.fragments.account.view.AccountFragment
import com.dukena.myapplication.fragments.cartelera.view.CarteleraFragment
import com.dukena.myapplication.fragments.carteleras_movies.view.CartelerasMoviesFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView

class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mActivityFragment: Fragment
    private lateinit var progressDialog: androidx.appcompat.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
    }


    private fun setupBottomNav() {
        mFragmentManager = supportFragmentManager
        val accountFragment = AccountFragment.newInstance()
        val carteleraFragment = CarteleraFragment.newInstance()
        val carteleraMoviewFragment= CartelerasMoviesFragment.newInstance()

        mActivityFragment = accountFragment

        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_home, accountFragment, AccountFragment::class.java.name)
            .commit()
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_home, carteleraFragment, CarteleraFragment::class.java.name)
            .hide(carteleraFragment)
            .commit()
        mFragmentManager
            .beginTransaction()
            .add(R.id.frame_home, carteleraMoviewFragment, CartelerasMoviesFragment::class.java.name)
            .hide(carteleraMoviewFragment)
            .commit()

        mBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_profile -> {
                    if (mActivityFragment !is AccountFragment) {
                        mFragmentManager.beginTransaction().hide(mActivityFragment)
                            .show(accountFragment).commit()
                        mActivityFragment = accountFragment
                        true
                    } else false
                }
                R.id.action_cinema -> {
                    if (mActivityFragment !is CarteleraFragment) {
                        mFragmentManager.beginTransaction().hide(mActivityFragment)
                            .show(carteleraFragment).commit()
                        mActivityFragment = carteleraFragment
                        true
                    } else false
                }
                R.id.action_cartelera ->{
                    if (mActivityFragment !is CartelerasMoviesFragment) {
                        mFragmentManager.beginTransaction().hide(mActivityFragment)
                            .show(carteleraMoviewFragment).commit()
                        mActivityFragment = carteleraMoviewFragment
                        true
                    } else false
                }
                else -> false
            }
        }
    }

    fun InstanceLoadingDialog(message: String){
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog,null)
        var tvMessage = dialogView.findViewById<MaterialTextView>(R.id.tv_message)
        tvMessage.text = message
        val progressBuilderDialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setCancelable(false)
        progressDialog = progressBuilderDialog.show()
    }
    fun hideDialog(){
        if(progressDialog != null){
            progressDialog.dismiss()
        }
    }

    fun InstanceDialog(message: String, title: String, btnText: String){
        if(progressDialog != null){
            progressDialog.dismiss()
        }
        val progressBuilderDialog = MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton(btnText,null)
            .setCancelable(false)
        progressDialog = progressBuilderDialog.show()
    }
}