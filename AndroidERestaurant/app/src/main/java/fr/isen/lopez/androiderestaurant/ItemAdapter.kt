package fr.isen.lopez.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lopez.androiderestaurant.databinding.CellMainBinding

class ItemAdapter(val items: List<String>,val itemClickListener:(String)-> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
// string car pour listant item 1,item2 etc ce sont des string apres il faudra changer
    class ItemViewHolder(binding:CellMainBinding): RecyclerView.ViewHolder(binding.root){
        val titlePlat: TextView = binding.list

        val layout: ConstraintLayout = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //créer le view holder et attacher le layout a celui-ci
        val binding = CellMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        //appelé au moment de l'affichage de la cellule
        val item = items[position]
        viewHolder.titlePlat.text = item

        viewHolder.layout.setOnClickListener {
            itemClickListener.invoke(item)
        }



    }

    override fun getItemCount(): Int {
        //on ifore a la recycle view combien d'item nous avons dans la liste
        return items.count()
    }
}