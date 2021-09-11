package ademar.leetcode.page.detail

import ademar.leetcode.model.Problem

sealed class DetailState

object DetailLoad : DetailState()

data class DetailError(
    val message: String,
) : DetailState()

data class DetailSuccess(
    val problem: Problem,
) : DetailState()
