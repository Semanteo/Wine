package fr.semanteo.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.semanteo.myapplication.MainActivity
import fr.semanteo.myapplication.PlantRepository.Singleton.plantList
import fr.semanteo.myapplication.R
import fr.semanteo.myapplication.adapter.PlantAdapter
import fr.semanteo.myapplication.adapter.PlantItemDecoration

class CollectionFragment(private val context: MainActivity): Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = PlantAdapter(context, plantList.filter { it.liked }, R.layout.item_vertical_wine)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}