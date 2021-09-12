package ademar.leetcode.page.detail

import ademar.leetcode.R
import ademar.leetcode.usecase.RxFactoryUseCase
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), Detail.View {

    private val subs by lazy { rxFactory.makeCompositeDisposable() }

    @Inject lateinit var interactor: DetailInteractor
    @Inject lateinit var rxFactory: RxFactoryUseCase

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

        subs.add(interactor.output.subscribe(::input))
        interactor.bind(this)
        output.onNext(Detail.Command.Create(intent.extras?.getLong(PROBLEM_ID)))
    }

    override fun onDestroy() {
        super.onDestroy()
        interactor.unBind()
        subs.clear()
    }

    override fun input(state: Detail.State) = when (state) {
        is Detail.State.Error -> {
            load.visibility = GONE
            error.visibility = VISIBLE
            content.visibility = GONE

            error.text = state.message
        }

        is Detail.State.Load -> {
            load.visibility = VISIBLE
            error.visibility = GONE
            content.visibility = GONE
        }

        is Detail.State.Success -> {
            load.visibility = GONE
            error.visibility = GONE
            content.visibility = VISIBLE

            val problem = state.problem
            title.text = problem.title
            description.text = problem.description
            image.setImageResource(problem.difficulty.image)
        }
    }

    override val output: Subject<Detail.Command> by lazy {
        rxFactory.makeBehaviourSubject(Detail.Command.Initial)
    }

}
