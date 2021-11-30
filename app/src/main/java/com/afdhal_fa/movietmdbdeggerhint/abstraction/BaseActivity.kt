package com.afdhal_fa.movietmdbdeggerhint.abstraction

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afdhal_fa.movietmdbdeggerhint.data.vo.HttpResult
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewDataBinding: B

    val binding: B
        get() = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        mViewDataBinding.lifecycleOwner = this
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onPositiveButtonPressed()
            }
            .show()
    }

    @Suppress("LongParameterList")
    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {},
        negativeButtonText: String = "Cancel",
        onNegativeButtonPressed: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                onPositiveButtonPressed()
            }
            .setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                onNegativeButtonPressed()
            }
            .show()
    }

    @Suppress("LongParameterList")
    fun nonCancelableAlertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {},
        negativeButtonText: String = "Cancel",
        onNegativeButtonPressed: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onPositiveButtonPressed()
            }
            .setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onNegativeButtonPressed()
            }
            .show()
    }

    fun showNoConnectionAlert(
        errorMessage: String = "Tidak ada koneksi internet. Mohon periksa kembali koneksi internet Anda",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun showServerErrorAlert(
        errorMessage: String = "Terjadi kendala pada server. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun showClientAlert(
        errorMessage: String = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun showTimeoutAlert(
        errorMessage: String = "Koneksi timeout. Mohon coba beberapa saat lagi",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
            "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
            "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
        imageResourceId: Int,
        retryButtonTitle: String = "Coba lagi",
        retryAction: () -> Unit = {}
    ) {
        snackbar(errorMessage)
    }

    fun snackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    fun longSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
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
}
