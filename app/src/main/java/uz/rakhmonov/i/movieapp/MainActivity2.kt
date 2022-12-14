package uz.rakhmonov.i.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.rakhmonov.i.movieapp.databinding.ActivityMain2Binding
import uz.rakhmonov.i.movieapp.models.myMovie
import uz.rakhmonov.i.movieapp.sharedPref.my_sharedPreference

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Add movies"
        my_sharedPreference.init(this)

        binding.apply {
            btnSave.setOnClickListener {
                if (edtName.text.isNotEmpty()&&edtAuthor.text.isNotEmpty()&&edtAbout.text.isNotEmpty()&&edtDate.text.isNotEmpty()) {
                    val movie = myMovie(
                        edtName.text.toString().trim(),
                        edtAuthor.text.toString().trim(),
                        edtAbout.text.toString().trim(),
                        edtDate.text.toString().trim(),
                    )
                    val list = my_sharedPreference.catchList
                    list.add(movie)
                    Toast.makeText(this@MainActivity2, "saqlandi", Toast.LENGTH_SHORT).show()
                    my_sharedPreference.catchList = list
                    finish()
                }else{
                    Toast.makeText(this@MainActivity2, "Iltimos, barcha qatorlarni to'ldiring", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}