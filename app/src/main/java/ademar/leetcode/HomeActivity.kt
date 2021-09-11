package ademar.leetcode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = HomeAdapter(
            items = listOf(
                HomeItem(
                    title = "1. Two Sum",
                    description = """
                        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
                        You may assume that each input would have exactly one solution, and you may not use the same element twice.
                        You can return the answer in any order. 
                    """.trimIndent(),
                    difficulty = Difficulty.EASY,
                ),
            ),
            callback = {
                Log.d("Home", "$it")
            }
        )
    }

}
