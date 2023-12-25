package com.example.uas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.api.UnivResponseItem
import com.mobile.mp3_final.repository.UnivRespos

class UnivAdapter(
    private val chara: List<UnivResponseItem>,
    private val univRespos: UnivRespos
) : RecyclerView.Adapter<UnivAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_univ, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chara.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = chara[position]
        holder.name.text = data.name
        holder.country.text = data.country

        holder.favorite.setOnClickListener {
            val dataInsert = chara[position]
            univRespos.insertIfNotExists(dataInsert.name, dataInsert.country)
            Toast.makeText(holder.itemView.context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_univ_name)
        val country: TextView = view.findViewById(R.id.tv_univ_country)
        val favorite: ImageButton = view.findViewById(R.id.btn_favorite)

        init {
            favorite.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val data = chara[position]
                    // Handle ImageButton click
                    univRespos.insertIfNotExists(data.name, data.country)
                    Toast.makeText(view.context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
