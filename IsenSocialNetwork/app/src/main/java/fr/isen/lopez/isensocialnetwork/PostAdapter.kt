package fr.isen.lopez.isensocialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lopez.isensocialnetwork.databinding.ActivityMainBinding
import fr.isen.lopez.isensocialnetwork.databinding.PostCellBinding

class PostAdapter(var posts: ArrayList<String>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding: PostCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = PostCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textView.text = posts[position]
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(binding: PostCellBinding): RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.postTitle
    }

}