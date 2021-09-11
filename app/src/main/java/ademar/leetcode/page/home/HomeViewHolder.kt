package ademar.leetcode.page.home

import ademar.leetcode.model.Problem
import ademar.leetcode.R
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeViewHolder(
    view: View,
    private val callback: (Problem) -> Unit,
) : RecyclerView.ViewHolder(view) {

    private var item: Problem? = null

    init {
        view.setOnClickListener {
            item?.let(callback)
        }
    }

    fun bind(item: Problem) {
        this.item = item
        val title = itemView.findViewById<TextView>(R.id.title)
        val difficulty = itemView.findViewById<ImageView>(R.id.difficulty)

        title.text = item.title
        difficulty.contentDescription = context.getString(item.difficulty.contentDescription)
        difficulty.setImageResource(item.difficulty.image)
    }

    private val context: Context
        get() = itemView.context

}
