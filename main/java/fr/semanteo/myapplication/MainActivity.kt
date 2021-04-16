package fr.semanteo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.semanteo.myapplication.fragments.AddPlantFragment
import fr.semanteo.myapplication.fragments.CollectionFragment
import fr.semanteo.myapplication.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this), R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_wine_page -> {
                    loadFragment(AddPlantFragment(this), R.string.add_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


    }
    private fun loadFragment(fragment: Fragment, string: Int) {
        val repo = PlantRepository()

        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        repo.updateData{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()}
    }
}