package fr.isen.lopez.androiderestaurant.Basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.lopez.androiderestaurant.R
import fr.isen.lopez.androiderestaurant.User.UserActivity
import fr.isen.lopez.androiderestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadlist()

        binding.orderButton.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity((intent))
        }
    }
    private fun loadlist(){
        val basket = Basket.getBasket(this)
        val items= Basket.getBasket(this).items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadlist()

        }
    }
}