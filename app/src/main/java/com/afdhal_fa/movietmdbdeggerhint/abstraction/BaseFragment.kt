package com.afdhal_fa.movietmdbdeggerhint.abstraction

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afdhal_fa.movietmdbdeggerhint.data.vo.HttpResult
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<out B : ViewDataBinding, V : ViewModel> : Fragment() {
    val binding: B
        get() = mViewDataBinding

    lateinit var vm: V

    lateinit var factory: ViewModelProvider.Factory

    private lateinit var mViewDataBinding: B

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        vm = ViewModelProvider(this, factory).get(getViewModelClass())
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewDataBinding.executePendingBindings()
        return mViewDataBinding.root
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int
    abstract fun getViewModelClass(): Class<V>

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {}
    ) {
        if (isAdded) {
            AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    onPositiveButtonPressed()
                }
                .show()
        }
    }

    fun nonCancelableAlertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {}
    ) {
        if (isAdded) {
            AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    onPositiveButtonPressed()
                }
                .show()
        }
    }

    fun showNoConnectionAlert(
        errorMessage: String = "Tidak ada koneksi internet. Mohon periksa kembali koneksi internet Anda",
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }

    fun showServerErrorAlert(
        errorMessage: String = "Terjadi kendala pada server. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }

    fun showClientAlert(
        errorMessage: String = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }

    fun showTimeoutAlert(
        errorMessage: String = "Koneksi timeout. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }

    fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
            "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }

    fun showErrorAlert(cause: HttpResult, retryAction: () -> Unit = {}) {
        when (cause) {
            HttpResult.NO_CONNECTION -> showNoConnectionAlert(retryAction = retryAction)
            HttpResult.TIMEOUT -> showTimeoutAlert(retryAction = retryAction)
            HttpResult.CLIENT_ERROR -> showClientAlert(retryAction = retryAction)
            HttpResult.BAD_RESPONSE -> showUnknownErrorAlert(retryAction = retryAction)
            HttpResult.SERVER_ERROR -> showServerErrorAlert(retryAction = retryAction)
            HttpResult.NOT_DEFINED -> showUnknownErrorAlert(retryAction = retryAction)
        }
    }

    fun snackBarError(message: String) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
