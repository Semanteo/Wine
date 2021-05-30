package fr.semanteo.myapplication

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.semanteo.myapplication.adapter.PlantAdapter

class PlantPopup(private val adapter: PlantAdapter, private val currentPlant: PlantModel) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_wine_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupWineButton()
        setupStarButton()
    }
    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            dismiss()
        }
    }

    private fun updateStar(button: ImageView) {
        if(currentPlant.liked) {
            button.setImageResource(R.drawable.ic_like)
        }
        else{
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun updateWine(button: ImageView) {
        if(currentPlant.drink) {
            button.setImageResource(R.drawable.ic_wine)
        }
        else{
            button.setImageResource(R.drawable.ic_unwine)
        }
    }

    private fun setupStarButton() {
        val starButton = findViewById<ImageView>(R.id.star_button)

        updateStar(starButton)

        starButton.setOnClickListener {
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)

            updateStar(starButton)
        }
    }

    private fun setupWineButton() {
        val wineButton = findViewById<ImageView>(R.id.wine_button)

        updateWine(wineButton)

        wineButton.setOnClickListener {
            currentPlant.drink = !currentPlant.drink
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)

            updateWine(wineButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            val repo = PlantRepository()
            repo.deleteWine(currentPlant)
            dismiss()
        }
    }

    private fun setupComponents() {
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        findViewById<TextView>(R.id.popup_wine_name).text = currentPlant.name

        findViewById<TextView>(R.id.popup_wine_description_subtitle).text = currentPlant.appellation

        findViewById<TextView>(R.id.popup_wine_type_subtitle).text = currentPlant.type

        findViewById<TextView>(R.id.popup_wine_year_subtitle).text = currentPlant.year.toString()

        findViewById<TextView>(R.id.popup_wine_number_subtitle).text = currentPlant.number.toString()
    }

}