package uz.rakhmonov.i.movieapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.rakhmonov.i.movieapp.adapters.myMovieAdapter
import uz.rakhmonov.i.movieapp.databinding.ActivityEditBinding
import uz.rakhmonov.i.movieapp.databinding.ActivityMain2Binding
import uz.rakhmonov.i.movieapp.models.myMovie
import uz.rakhmonov.i.movieapp.sharedPref.my_sharedPreference

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    lateinit var list:ArrayList<myMovie>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title="Edit movie"

        my_sharedPreference.init(this)
        val ListInfo=intent.getSerializableExtra("keyword") as myMovie
        val index = intent.getIntExtra("index",0)
        Toast.makeText(this, ListInfo.toString(), Toast.LENGTH_SHORT).show()

        binding.edtName.setText(ListInfo.name)
        binding.edtAuthor.setText(ListInfo.author)
        binding.edtAbout.setText(ListInfo.about)
        binding.edtDate.setText(ListInfo.date)




        binding.apply {
            binding.btnEdit.setOnClickListener {
                if (edtName.text.isNotEmpty() && edtAuthor.text.isNotEmpty() && edtAbout.text.isNotEmpty() && edtDate.text.isNotEmpty()) {
                    val movie = myMovie(binding.edtName.text.toString(),binding.edtAuthor.text.toString(),binding.edtAbout.text.toString(),binding.edtDate.text.toString())
                    val list =my_sharedPreference.catchList
                    list[index]=movie
                    my_sharedPreference.catchList=list

                    Toast.makeText(this@EditActivity, "saqlandi", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditActivity, "Iltimos, barcha qatorlarni to'ldiring", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}