package ademar.leetcode

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeViewHolder(
    view: View,
    private val callback: (HomeItem) -> Unit,
) : RecyclerView.ViewHolder(view) {

    private var item: HomeItem? = null

    init {
        view.setOnClickListener {
            item?.let(callback)
        }
    }

    fun bind(item: HomeItem) {
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
