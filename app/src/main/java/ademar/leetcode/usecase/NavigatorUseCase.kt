package ademar.leetcode.usecase

import ademar.leetcode.page.detail.DetailActivity
import ademar.leetcode.page.detail.PROBLEM_ID
import android.app.Activity
import android.content.Intent
import java.lang.ref.WeakReference

class NavigatorUseCase(
    private val host: WeakReference<Activity>,
) {
    fun openDetail(problemId: Long): Boolean {
        val activity = host.get()
        return if (activity == null) {
            false
        } else {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(PROBLEM_ID, problemId)
            activity.startActivity(intent)
            true
        }
    }
}
