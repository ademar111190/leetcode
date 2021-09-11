package ademar.leetcode.usecase

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxFactoryUseCase @Inject constructor() {

    fun makeCompositeDisposable() = CompositeDisposable()

    fun <T> makeBehaviourSubject(default: T): Subject<T> = BehaviorSubject.createDefault(default)

}
