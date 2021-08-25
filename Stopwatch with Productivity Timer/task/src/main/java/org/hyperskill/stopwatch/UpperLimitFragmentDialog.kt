package org.hyperskill.stopwatch

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData

class UpperLimitFragmentDialog(
    private val upperLimit: MutableLiveData<Long?>
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val contentView = LayoutInflater.from(requireActivity())
            .inflate(R.layout.fragment_upper_limit, null, false)
        val upperLimitView = contentView.findViewById<EditText>(R.id.upperLimitEditText)
        return AlertDialog.Builder(requireActivity())
            .setTitle("Set upper limit in seconds")
            .setView(contentView)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val sLimit = upperLimitView.text.toString()
                val limit = if (sLimit.isEmpty()) null else sLimit.toLong() * 1_000
                upperLimit.postValue(limit)
                dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                Log.d("fragment", "cancelled")
                dismiss()
            }
            .create()
    }
}