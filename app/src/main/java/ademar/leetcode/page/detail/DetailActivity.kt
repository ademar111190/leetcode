package ademar.leetcode.page.detail

import ademar.leetcode.R
import ademar.leetcode.model.Difficulty
import ademar.leetcode.model.Problem
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val problem = Problem(
            title = "1. Two Sum",
            description = """
                        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
                        You may assume that each input would have exactly one solution, and you may not use the same element twice.
                        You can return the answer in any order. 
                    """.trimIndent(),
            difficulty = Difficulty.EASY,
        )

        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val image = findViewById<ImageView>(R.id.difficulty)

        title.text = problem.title
        description.text = problem.description
        image.setImageResource(problem.difficulty.image)
    }

}
