package ademar.leetcode.di

import ademar.leetcode.model.Problem
import ademar.leetcode.storage.ProblemStorage
import android.util.LruCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * SingletonComponent @Singleton
 * ActivityRetainedComponent @ActivityRetainedScoped // ServiceComponent @ServiceScoped
 * ActivityComponent @ActivityScoped
 * FragmentComponent @FragmentScoped // ViewComponent @ViewScoped
 * ViewWithFragmentComponent @ViewScoped
 */
@[Module InstallIn(SingletonComponent::class)]
class SingletonModule {
    @[Provides Singleton]
    fun providesProblemStorage() = ProblemStorage(
        LruCache<Long, Problem>(4),
    )
}
