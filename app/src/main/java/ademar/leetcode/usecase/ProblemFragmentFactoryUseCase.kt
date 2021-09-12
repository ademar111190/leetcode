package ademar.leetcode.usecase

import ademar.leetcode.model.ADD_TWO_NUMBERS
import ademar.leetcode.model.TWO_SUM
import ademar.leetcode.page.problem.ProblemNotFoundFragment
import ademar.leetcode.page.problem.addtwonumbers.AddTwoNumbersFragment
import ademar.leetcode.page.problem.twosum.TwoSumFragment
import androidx.fragment.app.Fragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProblemFragmentFactoryUseCase @Inject constructor() {

    operator fun invoke(problemId: Long): Fragment {
        val fragmentClass = fragmentIdMap[problemId] ?: ProblemNotFoundFragment::class.java
        return fragmentClass.newInstance()
    }

    private val fragmentIdMap = mapOf(
        TWO_SUM to TwoSumFragment::class.java,
        ADD_TWO_NUMBERS to AddTwoNumbersFragment::class.java,
    )

}
