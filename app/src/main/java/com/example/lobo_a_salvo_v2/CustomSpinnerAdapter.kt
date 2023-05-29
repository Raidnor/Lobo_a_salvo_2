package com.example.lobo_a_salvo_v2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomSpinnerAdapter(context: Context, items: List<String>) : ArrayAdapter<String>(context, R.layout.text_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color))
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        view.setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color))
        return view
    }
}
