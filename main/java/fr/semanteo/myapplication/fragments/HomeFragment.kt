package fr.semanteo.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.semanteo.myapplication.MainActivity
import fr.semanteo.myapplication.PlantModel
import fr.semanteo.myapplication.PlantRepository.Singleton.plantList
import fr.semanteo.myapplication.R
import fr.semanteo.myapplication.adapter.PlantAdapter
import fr.semanteo.myapplication.adapter.PlantItemDecoration

class HomeFragment(
        private val context: MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList.filter { !it.drink }, R.layout.item_horizontal_wine)


        val veticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        veticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_wine)

        veticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}