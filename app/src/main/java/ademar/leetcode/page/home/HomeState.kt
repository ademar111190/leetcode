package ademar.leetcode.page.home

import ademar.leetcode.model.Problem

sealed class HomeState

object HomeLoad : HomeState()

data class HomeError(
    val message: String,
) : HomeState()

data class HomeSuccess(
    val items: List<Problem>,
) : HomeState()
