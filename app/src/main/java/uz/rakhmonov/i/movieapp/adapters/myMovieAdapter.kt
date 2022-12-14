package uz.rakhmonov.i.movieapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.i.movieapp.ShowActivity
import uz.rakhmonov.i.movieapp.databinding.ItemRvBinding
import uz.rakhmonov.i.movieapp.models.myMovie

class myMovieAdapter( val context: Context, val list: List<myMovie>, val rVclick: RVclick):RecyclerView.Adapter<myMovieAdapter.VH>() {

    inner class VH( val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myMovie: myMovie,position: Int){
            itemRvBinding.name.text=myMovie.name
            itemRvBinding.author.text=myMovie.author
            itemRvBinding.date.text=myMovie.date

            itemRvBinding.btnEdit.setOnClickListener {
                rVclick.edit(myMovie,position)
            }
            itemRvBinding.btnDelete.setOnClickListener {
                rVclick.delete(myMovie,position)

            }
            itemRvBinding.root.setOnClickListener {
                val intent=Intent(context, ShowActivity::class.java)
                val infoList=list[position]
                intent.putExtra("info",infoList)

                context.startActivity(intent)
            }


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {
        return list.size   }
}
interface RVclick{
    fun edit(movie: myMovie,position: Int)
    fun delete(Movie: myMovie,position: Int)
}