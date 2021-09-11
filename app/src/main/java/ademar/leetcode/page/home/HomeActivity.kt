package ademar.leetcode.page.home

import ademar.leetcode.R
import ademar.leetcode.model.Difficulty
import ademar.leetcode.model.Problem
import ademar.leetcode.page.detail.DetailActivity
import android.content.Intent
import android.os.Bundle
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
                Problem(
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
                startActivity(Intent(this, DetailActivity::class.java))
            }
        )
    }

}
