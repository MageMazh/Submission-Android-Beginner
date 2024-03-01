package com.d121211069.ghiblimagic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.Film

class MainActivity : AppCompatActivity() {
    companion object {
        private const val DATA = "data_detail"
    }

    private lateinit var rvFilm: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        rvFilm = findViewById(R.id.rv_film);
        rvFilm.setHasFixedSize(true)

        list.addAll(getListFilm())

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFilm(): ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataDuration = resources.getStringArray(R.array.data_time)
        val dataScore = resources.getStringArray(R.array.data_score)
        val dataDirector = resources.getStringArray(R.array.data_director)
        val dataProducer = resources.getStringArray(R.array.data_producer)
        val dataWiki = resources.getStringArray(R.array.data_wiki)
        val listFilm = ArrayList<Film>()
        for (i in dataName.indices) {
            val film = Film(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataYear[i],
                dataScore[i],
                dataDuration[i],
                dataDirector[i],
                dataProducer[i],
                dataWiki[i]
            )
            listFilm.add(film)
        }
        return listFilm
    }

    private fun showRecyclerList() {
        rvFilm.layoutManager = GridLayoutManager(this, 2)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilm.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DATA, data)
                startActivity(intentToDetail)
            }
        })
    }
}