package fr.semanteo.myapplication.adapter

import android.net.Uri
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.semanteo.myapplication.*

class PlantAdapter(
        val context : MainActivity,
        private val plantList: List<PlantModel>,
        private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val wineImage = view.findViewById<ImageView>(R.id.image_item)
        val wineName:TextView? = view.findViewById(R.id.name_item)
        val wineAppellation:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlant = plantList[position]

        val repo = PlantRepository()

        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.wineImage)

        holder.wineName?.text = currentPlant.name

        holder.wineAppellation?.text = currentPlant.appellation

        if(currentPlant.liked){
            holder.starIcon.setImageResource(R.drawable.ic_like)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }

        holder.starIcon.setOnClickListener{
            currentPlant.liked = !currentPlant.liked
            repo.updatePlant(currentPlant)
        }


        holder.itemView.setOnClickListener{
            PlantPopup(this, currentPlant).show()
        }

    }

    override fun getItemCount(): Int = plantList.size
}