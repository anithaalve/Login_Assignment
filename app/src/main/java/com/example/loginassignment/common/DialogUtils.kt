package com.example.loginassignment.common

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.loginassignment.R
import com.example.loginassignment.databinding.AlertDialogLayoutBinding

class DialogUtils {
  
  private lateinit var mDialog: AlertDialog
  
  private fun showDialog(context: Context?, title: String, msg: String) {
    val alertDialogLayoutBinding: AlertDialogLayoutBinding = DataBindingUtil.inflate(
      LayoutInflater.from(context),
      R.layout.alert_dialog_layout, null, false
    )
    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setView(alertDialogLayoutBinding.root)
    alertDialogBuilder.setCancelable(false)
    mDialog = alertDialogBuilder.create()
    alertDialogLayoutBinding.tvTitle.text = title
    alertDialogLayoutBinding.tvMsg.text = msg
    val btnOk: Button = alertDialogLayoutBinding.btnOk
    btnOk.setOnClickListener { view: View? -> mDialog.dismiss() }
    mDialog.show()
  }
  
  
  fun showDialog1(error: Int, context: Context?) {
    var msg = ""
    context?.let {
      var title = context.getString(R.string.error)
      when (error) {
        1 -> {
          msg = context.getString(R.string.error_message)
        }
        2 -> {
          msg = context.getString(R.string.user_doesnt_exist)
        }
        3 -> {
          title = context.getString(R.string.warning)
          msg = context.getString(R.string.check_password)
        }
        4 -> {
          msg = context.getString(R.string.already_exist)
        }
        else -> {
        }
      }
      showDialog(context, context.getString(R.string.error), msg)
    }
  }
}