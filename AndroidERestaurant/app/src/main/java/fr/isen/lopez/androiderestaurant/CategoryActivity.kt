package fr.isen.lopez.androiderestaurant
import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.lopez.androiderestaurant.HomeActivity.Companion.CategoryType
import fr.isen.lopez.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lopez.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

enum class LunchType {
    STARTER, MAIN, FINISH;

    companion object {
        fun getResString(type: LunchType): Int{
            return when(type){
                STARTER -> R.string.starters
                MAIN -> R.string.main
                FINISH -> R.string.finish
            }
        }

    }
}


class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryBinding
    lateinit var currentCategory: LunchType
    val fakeItems = listOf("Item1","Item2","Item3","Item4")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTilte()
        setupList()
        makeRequest()
        Log.d("life cycle", "CategoryActivity onCreate")
    }

    override fun onDestroy() {
        Log.d("life cycle", "CategoryActivity onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "CategoryActivity onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle", "CategoryActivity onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle", "CategoryActivity onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life cycle", "CategoryActivity onStop")
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL+NetworkConstants.MENU
        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP,NetworkConstants.SHOP)
        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
            Log.d("tag","{${it.toString()}")

            },
            {
                Log.d("tag","$it")

            })
        queue.add(request)
    }



    private fun setupTilte(){

        binding.title.text = getString(LunchType.getResString(currentCategory))
    }

    private fun setupList() {
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(fakeItems){item ->
            val intent = Intent(this@CategoryActivity,DetailActivity::class.java)
            //permet de rediriger vers une autre page quand on clique
            intent.putExtra(CategoryActivity.ItemSelected, item)
            startActivity(intent)

        }
        binding.itemRecyclerView.adapter = adapter
    }
    companion object{
        const val ItemSelected = "ItemSelected"
    }
}
