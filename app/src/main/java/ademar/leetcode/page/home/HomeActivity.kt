package ademar.leetcode.page.home

import ademar.leetcode.R
import ademar.leetcode.usecase.RxFactoryUseCase
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), Home.View {

    private val subs by lazy { rxFactory.makeCompositeDisposable() }

    @Inject lateinit var interactor: HomeInteractor
    @Inject lateinit var rxFactory: RxFactoryUseCase

    private val recycler: RecyclerView
        get() = findViewById(R.id.recycler)

    private val load: View
        get() = findViewById(R.id.load)

    private val error: TextView
        get() = findViewById(R.id.error)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        subs.add(interactor.output.subscribe(::input))
        interactor.bind(this)
        output.onNext(Home.Command.Create)
    }

    override fun onDestroy() {
        super.onDestroy()
        interactor.unBind()
        subs.clear()
    }

    override fun input(state: Home.State) = when (state) {
        is Home.State.Load -> {
            load.visibility = VISIBLE
            error.visibility = GONE
            recycler.visibility = GONE
        }

        is Home.State.Error -> {
            load.visibility = GONE
            error.visibility = VISIBLE
            recycler.visibility = GONE

            error.text = state.message
        }

        is Home.State.Success -> {
            load.visibility = GONE
            error.visibility = GONE
            recycler.visibility = VISIBLE

            recycler.adapter = HomeAdapter(
                items = state.items,
                callback = { problem ->
                    output.onNext(Home.Command.ProblemSelected(problem))
                }
            )
        }
    }

    override val output: Subject<Home.Command> by lazy {
        rxFactory.makeBehaviourSubject(Home.Command.Initial)
    }

}
