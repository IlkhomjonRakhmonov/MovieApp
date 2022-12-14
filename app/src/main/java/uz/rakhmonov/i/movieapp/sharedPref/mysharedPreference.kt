package uz.rakhmonov.i.movieapp.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.rakhmonov.i.movieapp.models.myMovie

object my_sharedPreference {

    private const val NAME = "catch"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var catchList:ArrayList<myMovie>
    get() = jsondanArrayListga(preferences.getString("keyList", "[]")!!)
    set(value)= preferences.edit{
        if (value!=null){
            it.putString("keyList", arrayListdanjsonga(value))
        }
    }
    fun arrayListdanjsonga(list:ArrayList<myMovie>):String{
        var gson= Gson()
        return gson.toJson(list)

    }
    fun jsondanArrayListga(str:String):ArrayList<myMovie>{
        var gson=Gson()
        val type=object : TypeToken<ArrayList<myMovie>>(){}.type
        return gson.fromJson<ArrayList<myMovie>>(str, type)


    }
}
