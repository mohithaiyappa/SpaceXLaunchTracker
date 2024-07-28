package tk.mohithaiyappa.spacexlaunchtracker.util

sealed class NetworkResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()

    data class Failure(val exception: String) : NetworkResponse<Nothing>()
}
