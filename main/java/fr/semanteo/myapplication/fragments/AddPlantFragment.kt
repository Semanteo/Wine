package fr.semanteo.myapplication.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.semanteo.myapplication.MainActivity
import fr.semanteo.myapplication.PlantModel
import fr.semanteo.myapplication.PlantRepository
import fr.semanteo.myapplication.PlantRepository.Singleton.downloadUri
import fr.semanteo.myapplication.R
import java.util.*

class AddPlantFragment(private val context: MainActivity): Fragment() {

    private var file: Uri? = null
    private var uploadedImage: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_wine, container, false)

        uploadedImage = view.findViewById(R.id.preview_image)

        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        pickupImageButton.setOnClickListener { pickupImage() }

        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener { sendForm(view) }

        return view
    }

    private fun sendForm(view: View) {
        val repo = PlantRepository()
        repo.uploadImage(file!!) {
            val wineName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val wineAppellation = view.findViewById<EditText>(R.id.appellation_input).text.toString()
            val type = view.findViewById<Spinner>(R.id.type_spinner).selectedItem.toString()
            val year = view.findViewById<Spinner>(R.id.year_spinner).selectedItem.toString().toInt()
            val number = view.findViewById<Spinner>(R.id.number_spinner).selectedItem.toString().toInt()
            val downloadImageUrl = downloadUri

            val plant = PlantModel(
                UUID.randomUUID().toString(),
                wineName,
                wineAppellation,
                downloadImageUrl.toString(),
                year,
                type,
                false,
                number,
                false
            )

            repo.insertPlant(plant)
        }

    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null) return

            file = data.data

            uploadedImage?.setImageURI(file)
        }
    }
}