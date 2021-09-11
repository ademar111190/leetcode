package ademar.leetcode.page.home

import ademar.leetcode.model.Problem
import io.reactivex.rxjava3.subjects.Subject

interface Home {

    interface View {
        fun input(state: State)
        val output: Subject<Command>
    }

    sealed class Command {
        object Create : Command()
        data class ProblemSelected(
            val problem: Problem,
        ) : Command()
    }

    sealed class State {
        object Load : State()

        data class Error(
            val message: String,
        ) : State()

        data class Success(
            val items: List<Problem>,
        ) : State()
    }

}
