package ademar.leetcode.model

import ademar.leetcode.R
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class Difficulty(
    @DrawableRes val image: Int,
    @StringRes val contentDescription: Int,
) {
    EASY(
        R.drawable.ic_difficulty_easy,
        R.string.content_description_difficulty_easy,
    ),
    MEDIUM(
        R.drawable.ic_difficulty_medium,
        R.string.content_description_difficulty_medium,
    ),
    HARD(
        R.drawable.ic_difficulty_hard,
        R.string.content_description_difficulty_hard,
    );
}
