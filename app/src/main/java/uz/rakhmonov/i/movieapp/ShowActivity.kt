package uz.rakhmonov.i.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.rakhmonov.i.movieapp.databinding.ActivityShowBinding
import uz.rakhmonov.i.movieapp.models.myMovie
import uz.rakhmonov.i.movieapp.sharedPref.my_sharedPreference

class ShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityShowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        my_sharedPreference.init(this)
        
        val infoList=intent.getSerializableExtra("info") as myMovie
        supportActionBar?.title=infoList.name
        binding.apply {
            tvName.text="Name: ${infoList.name}"
            tvAuthor.text="Author:${infoList.author}"
            tvAbout.text="About: ${infoList.about}"
            tvDate.text="Date:${infoList.date}"
        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}