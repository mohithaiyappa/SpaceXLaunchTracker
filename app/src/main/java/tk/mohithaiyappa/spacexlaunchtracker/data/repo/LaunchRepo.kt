package tk.mohithaiyappa.spacexlaunchtracker.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response.LaunchItem
import tk.mohithaiyappa.spacexlaunchtracker.data.source.remote.LaunchService
import tk.mohithaiyappa.spacexlaunchtracker.util.Result
import javax.inject.Inject

class LaunchRepo
    @Inject
    constructor(
        private val launchService: LaunchService,
    ) {
        suspend fun getLaunches(): Result<List<LaunchItem>> {
            return withContext(Dispatchers.IO) {
                try {
                    val launches = launchService.getLaunches()
                    Result.Success(launches)
                } catch (e: Exception) {
                    Result.Error(e.message ?: "An unknown error occurred")
                }
            }
        }
    }
