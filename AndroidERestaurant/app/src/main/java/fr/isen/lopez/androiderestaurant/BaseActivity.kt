package fr.isen.lopez.androiderestaurant

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.view.isVisible
import fr.isen.lopez.androiderestaurant.Basket.Basket


open class BaseActivity :AppCompatActivity(){
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val menuView = menu?.findItem(R.id.basket)?.actionView
        val countText = menuView?.findViewById(R.id.basketCount) as? TextView
        val count = getItemsCount()
        countText?.isVisible = count > 0
        countText?.text = count.toString()

        menuView?.setOnClickListener{
            if (count > 0){
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)


            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }
    private fun getItemsCount(): Int{
        val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCES_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Basket.ITEMS_COUNT,0)
    }
}