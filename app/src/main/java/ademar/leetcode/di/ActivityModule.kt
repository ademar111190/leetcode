package ademar.leetcode.di

import ademar.leetcode.di.ActivityModule.Declarations
import ademar.leetcode.page.detail.Detail
import ademar.leetcode.page.detail.DetailActivity
import ademar.leetcode.page.home.Home
import ademar.leetcode.page.home.HomeActivity
import ademar.leetcode.usecase.NavigatorUseCase
import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.lang.ref.WeakReference

/**
 * SingletonComponent @Singleton
 * ActivityRetainedComponent @ActivityRetainedScoped // ServiceComponent @ServiceScoped
 * ActivityComponent @ActivityScoped
 * FragmentComponent @FragmentScoped // ViewComponent @ViewScoped
 * ViewWithFragmentComponent @ViewScoped
 */
@[Module(includes = [Declarations::class]) InstallIn(ActivityComponent::class)]
class ActivityModule {

    @Provides
    fun provideNavigatorUseCase(a: Activity) = NavigatorUseCase(WeakReference(a))

    @[Module InstallIn(ActivityComponent::class)]
    interface Declarations {
        @Binds
        fun bindHomeView(a: HomeActivity): Home.View

        @Binds
        fun bindDetailView(a: DetailActivity): Detail.View
    }

}
