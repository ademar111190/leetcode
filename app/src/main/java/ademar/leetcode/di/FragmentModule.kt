package ademar.leetcode.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * SingletonComponent @Singleton
 * ActivityRetainedComponent @ActivityRetainedScoped // ServiceComponent @ServiceScoped
 * ActivityComponent @ActivityScoped
 * FragmentComponent @FragmentScoped // ViewComponent @ViewScoped
 * ViewWithFragmentComponent @ViewScoped
 */
@[Module InstallIn(FragmentComponent::class)]
class FragmentModule
