package poc.naveen.movies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import poc.naveen.movies.R
import poc.naveen.movies.data.model.Content
import poc.naveen.movies.utils.Helper

class MainAdapter(private val content: ArrayList<Content>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val item = content[position]

        holder.tvName.text = item.name

        Picasso.get()
            .load(Helper.getDrawableFromString(holder.ivPoster.context, item.posterImage.substringBeforeLast(".jpg")))
            .placeholder(R.drawable.placeholder_for_missing_posters)
            .error(R.drawable.placeholder_for_missing_posters)
            .into(holder.ivPoster)
    }

    override fun getItemCount(): Int = content.size

    fun updateContent(rows: List<Content>) {
        //this.content.clear()
        this.content.addAll(rows)
        notifyDataSetChanged()
    }
}