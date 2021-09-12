package ademar.leetcode.page.detail

import ademar.leetcode.storage.ProblemStorage
import ademar.leetcode.usecase.RxFactoryUseCase
import ademar.leetcode.usecase.ThrowableMessageUseCase
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject

class DetailInteractor @Inject constructor(
    private val problemStorage: ProblemStorage,
    private val messages: ThrowableMessageUseCase,
    rxFactory: RxFactoryUseCase,
) {

    private val subs = rxFactory.makeCompositeDisposable()
    val output: Subject<Detail.State> = rxFactory.makeBehaviourSubject(Detail.State.Load)

    fun bind(home: Detail.View) {
        subs.add(home.output.subscribe(::map) {
            output.onNext(Detail.State.Error(messages(it)))
        })
    }

    fun unBind() {
        subs.clear()
    }

    private fun map(command: Detail.Command) {
        when (command) {
            is Detail.Command.Initial -> {
                output.onNext(Detail.State.Load)
            }

            is Detail.Command.Create -> {
                output.onNext(Detail.State.Load)
                val problemId = command.problemId
                if (problemId == null) {
                    output.onNext(Detail.State.Error("No id found"))
                } else {
                    subs.add(problemStorage.get(problemId).subscribe({ problem ->
                        output.onNext(Detail.State.Success(problem))
                    }, {
                        output.onNext(Detail.State.Error(messages(it)))
                    }))
                }
            }
        }
    }

}
