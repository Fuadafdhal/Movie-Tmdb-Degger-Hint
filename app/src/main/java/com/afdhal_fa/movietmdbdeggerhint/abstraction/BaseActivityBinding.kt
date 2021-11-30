package com.afdhal_fa.movietmdbdeggerhint.abstraction

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivityBinding<B : ViewDataBinding> : AppCompatActivity() {
    private lateinit var _binding: B

    protected val binding: B
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        _binding.lifecycleOwner = this
        _binding.executePendingBindings()
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

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

    fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
            "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
        imageResourceId: Int,
        retryAction: () -> Unit = {}
    ) {
        snackBarError(errorMessage)
    }
    fun snackBarError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
