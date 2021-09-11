package ademar.leetcode.page.home

import ademar.leetcode.storage.ProblemStorage
import ademar.leetcode.usecase.NavigatorUseCase
import ademar.leetcode.usecase.ThrowableMessageUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class HomeInteractor(
    private val problemStorage: ProblemStorage,
    private val messages: ThrowableMessageUseCase,
    private val navigator: NavigatorUseCase,
) {

    private val subs = CompositeDisposable()
    val output = BehaviorSubject.createDefault<Home.State>(Home.State.Load)

    fun bind(home: Home.View) {
        subs.add(home.output.subscribe(::map) {
            output.onNext(Home.State.Error(messages(it)))
        })
    }

    fun unBind() {
        subs.clear()
    }

    private fun map(command: Home.Command) {
        when (command) {
            is Home.Command.Create -> {
                output.onNext(Home.State.Load)
                subs.add(problemStorage.fetchProblems().subscribe({ problems ->
                    output.onNext(Home.State.Success(problems))
                }, {
                    output.onNext(Home.State.Error(messages(it)))
                }))
            }
            is Home.Command.ProblemSelected -> {
                val problem = command.problem
                problemStorage.put(problem)
                if (!navigator.openDetail(problem.id)) {
                    output.onNext(Home.State.Error("Failed to launch $problem"))
                }
            }
        }
    }

}
