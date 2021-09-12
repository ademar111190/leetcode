package ademar.leetcode.page.detail

import ademar.leetcode.model.Problem
import io.reactivex.rxjava3.subjects.Subject

interface Detail {

    interface View {
        fun input(state: State)
        val output: Subject<Command>
    }

    sealed class Command {
        object Initial : Command()
        data class Create(
            val problemId: Long?,
        ) : Command()
    }

    sealed class State {
        object Load : State()

        data class Error(
            val message: String,
        ) : State()

        data class Success(
            val problem: Problem,
        ) : State()
    }

}
