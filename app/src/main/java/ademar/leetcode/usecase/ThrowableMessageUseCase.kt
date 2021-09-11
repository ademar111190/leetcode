package ademar.leetcode.usecase

import javax.inject.Inject

class ThrowableMessageUseCase @Inject constructor() {
    operator fun invoke(throwable: Throwable?): String {
        return if (throwable == null) {
            "No error message found!"
        } else {
            throwable.message ?: throwable.stackTraceToString()
        }
    }
}
