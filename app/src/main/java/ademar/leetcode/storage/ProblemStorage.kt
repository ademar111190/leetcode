package ademar.leetcode.storage

import ademar.leetcode.model.Difficulty.EASY
import ademar.leetcode.model.Difficulty.MEDIUM
import ademar.leetcode.model.Problem
import android.util.LruCache
import io.reactivex.rxjava3.core.Single

object ProblemStorage {

    private val lruCache = LruCache<Long, Problem>(4)

    fun fetchProblems(): Single<List<Problem>> {
        return Single.just(
            listOf(
                Problem(
                    id = 1L,
                    title = "1. Two Sum",
                    description = """
                        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
                        You may assume that each input would have exactly one solution, and you may not use the same element twice.
                        You can return the answer in any order. 
                    """.trimIndent(),
                    difficulty = EASY,
                ),
                Problem(
                    id = 2L,
                    title = "2. Add Two Numbers",
                    description = """
                        You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
                        You may assume the two numbers do not contain any leading zero, except the number 0 itself. 
                    """.trimIndent(),
                    difficulty = MEDIUM,
                ),
            ),
        )
    }

    fun put(problem: Problem): Single<Boolean> {
        return Single.just(problem)
            .doOnSubscribe {
                lruCache.put(problem.id, problem)
            }
            .map { true }
    }

    fun get(problemId: Long): Single<Problem> {
        return Single.just(problemId)
            .flatMap { id ->
                val problem = lruCache.get(id)
                if (problem != null) {
                    Single.just(listOf(problem))
                } else {
                    fetchProblems().map { problems ->
                        problems.filter { it.id == id }
                    }
                }
            }
            .filter { it.isNotEmpty() }
            .map { it.first() }
            .toSingle()
    }

}
