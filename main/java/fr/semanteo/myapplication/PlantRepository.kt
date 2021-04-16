package fr.semanteo.myapplication

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import fr.semanteo.myapplication.PlantRepository.Singleton.databaseRef
import fr.semanteo.myapplication.PlantRepository.Singleton.downloadUri
import fr.semanteo.myapplication.PlantRepository.Singleton.plantList
import fr.semanteo.myapplication.PlantRepository.Singleton.storageReference
import java.net.URI
import java.util.*
import kotlin.coroutines.Continuation

class PlantRepository {

    object Singleton{
        private val BUCKET_URL: String = "gs://wine-7d4e4.appspot.com"
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        val databaseRef = FirebaseDatabase.getInstance().getReference("wines")

        val plantList = arrayListOf<PlantModel>()

        var downloadUri: Uri? = null
    }

    fun updateData(callback: () -> Unit) {
        databaseRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot:  DataSnapshot) {

                plantList.clear()

                for(ds in snapshot.children){
                    val plant = ds.getValue(PlantModel::class.java)

                    if(plant != null){
                        plantList.add(plant)
                    }
                }
                callback()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun uploadImage(file: Uri, callback: () -> Unit) {
        if(file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            uploadTask.continueWithTask(com.google.android.gms.tasks.Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if(!task.isSuccessful){
                    task.exception?.let { throw it }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    downloadUri = task.result
                    callback()
                }
            }
            }
    }

    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun insertPlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun deleteWine(wine: PlantModel) = databaseRef.child(wine.id).removeValue()
}
