package com.example.plancton.ui.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.plancton.R

fun Fragment.showUnauthorizedDialog(reLogin: () -> Unit) {
    AlertDialog.Builder(requireContext()).apply {
        setTitle(R.string.authorisation_error)
        setMessage(R.string.authorisation_error_message)
        setPositiveButton(R.string.sign_in) { _, _ ->
            reLogin()
        }
        setOnDismissListener {
            reLogin()
        }
        create()
        show()
    }
}