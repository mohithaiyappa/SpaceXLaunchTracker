package tk.mohithaiyappa.spacexlaunchtracker.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response.LaunchItem
import tk.mohithaiyappa.spacexlaunchtracker.data.source.remote.LaunchService
import tk.mohithaiyappa.spacexlaunchtracker.util.NetworkResponse
import javax.inject.Inject

class LaunchRepo
    @Inject
    constructor(
        private val launchService: LaunchService,
    ) {
        suspend fun getLaunches(): NetworkResponse<List<LaunchItem>> {
            return withContext(Dispatchers.IO) {
                try {
                    val launches = launchService.getLaunches()
                    NetworkResponse.Success(launches)
                } catch (e: Exception) {
                    NetworkResponse.Failure(e.message ?: "An unknown error occurred")
                }
            }
        }
    }
