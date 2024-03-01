package com.d121211069.ghiblimagic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.d121211069.ghiblimagic.databinding.ActivityDetailBinding
import com.dicoding.myrecyclerview.Film

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val DATA = "data_detail"
    }

    private lateinit var binding: ActivityDetailBinding
    private var filmwiki: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Film>(DATA)

        filmwiki = data?.wiki

        if (data != null) {
            binding.filmTitle.text = data.name
            binding.filmYear.text = "(${data.year})"
            binding.filmDurationValue.text = konversiWaktu(data.duration.toInt()) + ":00"
            binding.filmRatingValue.text = "${data.score}/100"
            binding.directorValue.text = data.director
            binding.producerValue.text = data.producer
            data.let { binding.imgFilm.setImageResource(it.photo) }
            binding.descriptionText.text = data.description
        }

        findViewById<ImageView>(R.id.arrow_back).setOnClickListener {
            onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, filmwiki)
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun konversiWaktu(waktu: Int): String {
        val jumlahJam = waktu / 60
        val jumlahMenit = waktu % 60

        val hasilFormat = String.format("%02d:%02d", jumlahJam, jumlahMenit)
        return hasilFormat
    }
}