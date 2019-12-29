package com.example.savearraylistinsharedpreferenceswithgsonserializationanddeserialization

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ExampleAdaptor(var c: Context, var arrayList: ArrayList<CardViewData>) :
    RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder>() {

    class ExampleViewHolder : RecyclerView.ViewHolder {
        var tvTitle: TextView? = null
        var tvDescription: TextView? = null

        constructor(v: View) : super(v) {
            tvTitle = v.findViewById(R.id.tv_title)
            tvDescription = v.findViewById(R.id.tv_description)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        var inflater = LayoutInflater.from(c)
        var v = inflater.inflate(R.layout.cardview, parent, false)

        return ExampleViewHolder(v)


    }

    override fun getItemCount(): Int {
        return arrayList.size

    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        var list = arrayList.get(position)

        holder.run {
            tvTitle?.setText(list.title)
            tvDescription?.setText(list.description)
        }

    }
}