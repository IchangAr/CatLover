package com.example.catlovers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class deskripsi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deskripsi)
        supportActionBar?.hide()

        val catType = intent.getStringExtra("cat_type")
        val catDescription = intent.getStringExtra("cat_description")
        val catPhoto = intent.getIntExtra("cat_photo", 0)
        val catNameOther = intent.getStringExtra("name_other")
        val catCharacteristics = intent.getStringExtra("characteristics")

        val tvType = findViewById<TextView>(R.id.tv_item_type)
        val tvDescription = findViewById<TextView>(R.id.tv_item_description)
        val imgPhoto = findViewById<ImageView>(R.id.img_item_photo)
        val tvNameOther = findViewById<TextView>(R.id.tv_item_name)
        val tvCharacteristics = findViewById<TextView>(R.id.tv_item_characteristics)

        tvType.text = catType
        tvDescription.text = catDescription
        imgPhoto.setImageResource(catPhoto)
        tvNameOther.text = catNameOther
        tvCharacteristics.text = catCharacteristics

        val shareButton: Button = findViewById(R.id.action_share)
        shareButton.setOnClickListener {
            shareButton(catType, catDescription) // Memanggil fungsi share
        }

    }
    private fun shareButton(catType: String?, catDescription: String?) {

        val shareText = "Jenis Kucing: $catType\n\nDeskripsi: $catDescription"

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        // Menampilkan aplikasi yang mendukung berbagi
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Bagikan menggunakan"))
            }
        }
}
