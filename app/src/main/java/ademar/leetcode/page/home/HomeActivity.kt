package ademar.leetcode.page.home

import ademar.leetcode.R
import ademar.leetcode.page.detail.DetailActivity
import ademar.leetcode.page.detail.PROBLEM_ID
import ademar.leetcode.storage.ProblemStorage
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeActivity : AppCompatActivity() {

    val subs = CompositeDisposable()
    val storage = ProblemStorage

    private val recycler: RecyclerView
        get() = findViewById(R.id.recycler)

    private val load: View
        get() = findViewById(R.id.load)

    private val error: TextView
        get() = findViewById(R.id.error)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        setState(HomeLoad)
        subs.add(storage.fetchProblems().subscribe({ problems ->
            setState(HomeSuccess(problems))
        }, {
            setState(HomeError(it.message ?: it.stackTrace.toString()))
        }))
    }

    fun setState(state: HomeState) = when (state) {
        is HomeLoad -> {
            load.visibility = VISIBLE
            error.visibility = GONE
            recycler.visibility = GONE
        }

        is HomeError -> {
            load.visibility = GONE
            error.visibility = VISIBLE
            recycler.visibility = GONE

            error.text = state.message
        }

        is HomeSuccess -> {
            load.visibility = GONE
            error.visibility = GONE
            recycler.visibility = VISIBLE

            recycler.adapter = HomeAdapter(
                items = state.items,
                callback = { problem ->
                    storage.put(problem)
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(PROBLEM_ID, problem.id)
                    startActivity(intent)
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subs.clear()
    }

}
