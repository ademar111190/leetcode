package ademar.leetcode.usecase

class ThrowableMessageUseCase {
    operator fun invoke(throwable: Throwable?): String {
        return if (throwable == null) {
            "No error message found!"
        } else {
            throwable.message ?: throwable.stackTraceToString()
        }
    }
}
