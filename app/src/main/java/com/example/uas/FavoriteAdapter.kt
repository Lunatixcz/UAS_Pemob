package com.example.uas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.Univ
import com.example.uas.databinding.ItemRowFavBinding
import com.mobile.mp3_final.repository.UnivRespos

class FavoriteAdapter (
    private val univRespos: UnivRespos) :
    RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>(){

        private val listUniv = ArrayList<Univ>()

        inner class FavViewHolder (private val binding: ItemRowFavBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind (univ : Univ){
                with(binding){
                    tvItemName.text = univ.name
                    tvItemDescription.text = univ.country
                }
            }

            init {
                binding.deleteBtn.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val univToDelete = listUniv[position]

                        univToDelete.name?.let { name->
                            univToDelete.country?.let { country ->
                                univRespos.deleteExistingUniv(name, country)

                                listUniv.removeAt(position)
                                notifyItemRemoved(position)
                            }
                        }
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemRowFavBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return FavViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listUniv.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(listUniv[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUniv(univ: List<Univ>) {
        listUniv.clear()
        listUniv.addAll(univ)
        notifyDataSetChanged()
    }
}