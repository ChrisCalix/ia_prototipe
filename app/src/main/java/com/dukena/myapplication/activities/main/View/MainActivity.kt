package com.dukena.myapplication.activities.main.View

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.dukena.myapplication.R
import com.dukena.myapplication.activities.Home.HomeActivity
import com.dukena.myapplication.activities.main.view_model.MainViewModel
import com.dukena.myapplication.databinding.ActivityMainBinding
import com.dukena.myapplication.databinding.LoadingDialogBinding
import com.dukena.myapplication.utils.BaseApplication
import com.dukena.myapplication.utils.Constants
import com.dukena.myapplication.utils.Constants.Companion.KEY_TOKEN
import com.dukena.myapplication.utils.Constants.Companion.KEY_TOKEN_EXPIRE
import com.dukena.myapplication.utils.Constants.Companion.KEY_TOKEN_TYPE
import com.dukena.myapplication.utils.Utils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var progressDialog: androidx.appcompat.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        viewModel = ViewModelProvider(this).get()

        with(mBinding) {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
            //etPass.onEditorAction(EditorInfo.IME_ACTION_DONE)

            etPass.setOnEditorActionListener() { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    viewModel.callGetAuth()
                    true
                }
                false
            }

            with(viewModel) {
                loginResponse.observe(this@MainActivity, { loginResponse ->
                    Utils.hideKeyboard(mBinding.root)
                    startActivity(
                        Intent(
                            this@MainActivity,
                            HomeActivity::class.java
                        )


                    )//setear header con token y tipo de token
                })

                progressBar.observe(this@MainActivity, { showProgresBar ->
                    Utils.hideKeyboard(mBinding.root)
                    if (showProgresBar) {
                        val dialogView = layoutInflater.inflate(R.layout.loading_dialog,null)
                        val progressBuilderDialog = MaterialAlertDialogBuilder(this@MainActivity)
                            .setView(dialogView)
                            .setCancelable(false)
                        var tvMessage = dialogView.findViewById<MaterialTextView>(R.id.tv_message)
                        tvMessage.text = "Iniciando Sesion"
                        progressDialog = progressBuilderDialog.show()

                    } else {
                        if(progressDialog != null){
                            progressDialog.dismiss()
                        }
                    }
                })

                message.observe(this@MainActivity, {
                    Utils.hideKeyboard(mBinding.root)
                    if(progressDialog != null){
                        progressDialog.dismiss()
                    }
                    val progressBuilderDialog = MaterialAlertDialogBuilder(this@MainActivity)
                        .setMessage(it)
                        .setTitle("No se puede iniciar Sesion")
                        .setPositiveButton("ACEPTAR",null)
                        .setCancelable(false)
                    progressDialog = progressBuilderDialog.show()
                })
            }

            /*btnLogin.setOnClickListener {
                viewModel.callGetAuth(
                    etUser.text.toString(),
                    etPass.text.toString()
                )
                //
            }*/

        }


    }

    /*private fun callGetAuth() {
        val cinepolisService: CinepolisService =
            RestEngine.getRestEngine().create(CinepolisService::class.java)
        var loginRequest = LoginRequest("pruebas_beto_ia%40yahoo.com", "Pruebas01")

        //val result: Call<LoginRequest> = cinepolisService.getAuth("stage_HNYh3RaK_Test",loginRequest)
        val result: Call<LoginRequest> = cinepolisService.callGetAuthCorroutines(loginRequest)
        result.enqueue(object : Callback<LoginRequest> {
            override fun onFailure(call: Call<LoginRequest>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<LoginRequest>, response: Response<LoginRequest>) {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_LONG).show()
            }
        })
    }*/
}