package com.example.catlovers

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCat: RecyclerView
    private val list = ArrayList<Cat>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCat = findViewById(R.id.rv_cat)
        rvCat.setHasFixedSize(true)

        list.addAll(getListCat())
        showRecyclerList()
    }

    private fun getListCat(): ArrayList<Cat>{
        val dataType = resources.getStringArray(R.array.cat_type)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataNameOther = resources.getStringArray(R.array.name_other)
        val dataCharacteristics = resources.getStringArray(R.array.characteristics)

        val listCat = ArrayList<Cat>()
        for (i in dataType.indices) {
            val cat = Cat(dataType[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataNameOther[i], dataCharacteristics[i])
            listCat.add(cat)
        }
        dataPhoto.recycle()
        return listCat
    }
    private fun showRecyclerList(){
        rvCat.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListCatAdapter(list)
        rvCat.adapter = listCatAdapter

        listCatAdapter.setOnItemClickCallback(object : ListCatAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cat) {
                showSelectedCat(data)
            }
        })
    }
    private fun showSelectedCat(cat: Cat) {
        val intent = Intent(this, deskripsi::class.java)
        intent.putExtra("cat_type", cat.type)
        intent.putExtra("cat_description", cat.description)
        intent.putExtra("cat_photo", cat.photo)
        intent.putExtra("name_other", cat.nameOther)
        intent.putExtra("characteristics", cat.characteristics)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, about::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
