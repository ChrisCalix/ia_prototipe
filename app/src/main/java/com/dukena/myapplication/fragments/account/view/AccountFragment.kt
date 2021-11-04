package com.dukena.myapplication.fragments.account.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.dukena.myapplication.activities.Home.HomeActivity
import com.dukena.myapplication.databinding.FragmentAccountBinding
import com.dukena.myapplication.fragments.account.viewmodel.AccountViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView

class AccountFragment : Fragment() {

    private lateinit var mBinding: FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAccountBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get()

        with(mBinding) {
            accountViewModel = viewModel
            lifecycleOwner = this@AccountFragment
            viewModel.getProfile()

            with(viewModel) {
                progressBar.observe(viewLifecycleOwner, { showProgresBar ->
                    with(activity as HomeActivity) {
                        if (showProgresBar) {
                            InstanceLoadingDialog("Cargando")
                        } else {
                            hideDialog()
                        }
                    }
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
        fun newInstance() =
            AccountFragment().apply {

            }
    }
}