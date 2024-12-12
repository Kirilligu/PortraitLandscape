package com.example.portraitlandscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    private var currentImageIndex = 0
    private val images = listOf(
        R.drawable.car1,
        R.drawable.car2,
        R.drawable.car3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentImageIndex = savedInstanceState.getInt("currentImageIndex", 0)
            val imageView = findViewById<ImageView>(R.id.picture)
            imageView.setImageResource(images[currentImageIndex])
        }
        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)
        val spinner = findViewById<Spinner>(R.id.pictures_list)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentImageIndex", currentImageIndex)
    }

    fun onChangePictureClick(v: View) {
        val imageView = findViewById<ImageView>(R.id.picture)
        currentImageIndex = (currentImageIndex + 1) % images.size
        imageView.setImageResource(images[currentImageIndex])
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "Selected item $position", Toast.LENGTH_SHORT).show()
        val imageView = findViewById<ImageView>(R.id.picture)
        currentImageIndex = position
        imageView.setImageResource(
            when (position) {
                0 -> R.drawable.car1
                1 -> R.drawable.car2
                2 -> R.drawable.car3
                else -> R.drawable.car1
            }
        )
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show()
    }
}
