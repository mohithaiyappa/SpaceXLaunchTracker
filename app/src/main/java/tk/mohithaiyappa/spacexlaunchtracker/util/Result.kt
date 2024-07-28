package tk.mohithaiyappa.spacexlaunchtracker.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: String) : Result<Nothing>()

    data object Loading : Result<Nothing>()
}
