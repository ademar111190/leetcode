package ademar.leetcode.page.detail

import ademar.leetcode.R
import ademar.leetcode.storage.ProblemStorage
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DetailActivity : AppCompatActivity() {

    val subs = CompositeDisposable()
    val storage = ProblemStorage

    private val content: View
        get() = findViewById(R.id.content)

    private val load: View
        get() = findViewById(R.id.load)

    private val error: TextView
        get() = findViewById(R.id.error)

    private val title: TextView
        get() = findViewById(R.id.title)

    private val description: TextView
        get() = findViewById(R.id.description)

    private val image: ImageView
        get() = findViewById(R.id.difficulty)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        setState(DetailLoad)

        val problemId = intent.extras?.getLong(PROBLEM_ID)
        if (problemId == null) {
            setState(DetailError("No id found"))
        } else {
            storage.get(problemId).subscribe({ problem ->
                setState(DetailSuccess(problem))
            }, {
                setState(DetailError(it.message ?: it.stackTrace.toString()))
            })
        }
    }

    fun setState(state: DetailState) = when (state) {
        is DetailError -> {
            load.visibility = GONE
            error.visibility = VISIBLE
            content.visibility = GONE

            error.text = state.message
        }

        is DetailLoad -> {
            load.visibility = VISIBLE
            error.visibility = GONE
            content.visibility = GONE
        }

        is DetailSuccess -> {
            load.visibility = GONE
            error.visibility = GONE
            content.visibility = VISIBLE

            val problem = state.problem
            title.text = problem.title
            description.text = problem.description
            image.setImageResource(problem.difficulty.image)
        }
    }

}
