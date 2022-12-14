package uz.rakhmonov.i.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.i.movieapp.adapters.RVclick
import uz.rakhmonov.i.movieapp.adapters.myMovieAdapter
import uz.rakhmonov.i.movieapp.databinding.ActivityMainBinding
import uz.rakhmonov.i.movieapp.models.myMovie
import uz.rakhmonov.i.movieapp.sharedPref.my_sharedPreference

class MainActivity : AppCompatActivity(),RVclick {
    lateinit var binding: ActivityMainBinding
    lateinit var myMovieAdapter: myMovieAdapter
    lateinit var list:ArrayList<myMovie>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title="Movies"

        my_sharedPreference.init(this)
        list=my_sharedPreference.catchList

        myMovieAdapter= myMovieAdapter(this, list, this)
        binding.RecView.adapter=myMovieAdapter


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        list.clear()
        list.addAll(my_sharedPreference.catchList)
        myMovieAdapter.notifyDataSetChanged()
    }

    override fun edit(movie: myMovie, position: Int) {

        val ListInfo = list[position]
        val intent=Intent(this, EditActivity::class.java)
        intent.putExtra("keyword",ListInfo)
        intent.putExtra("index",position)
        startActivity(intent)

    }

    override fun delete(movie: myMovie, position: Int) {
        list.removeAt(position)
        my_sharedPreference.catchList=list
        myMovieAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "${movie.name} o'chirildi", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, MainActivity2::class.java))
        return super.onOptionsItemSelected(item)
    }



}