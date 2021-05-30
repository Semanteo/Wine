package fr.semanteo.myapplication

class PlantModel(
        val id: String = "wine1",
        val name: String = "Nom",
        val appellation: String = "Appellation",
        val imageUrl: String = "data:image/jpeg;base64,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",
        val year: Int = 2020,
        val type: String = "Rouge",
        var liked: Boolean = false,
        var number: Int = 1,
        var drink: Boolean = false
)