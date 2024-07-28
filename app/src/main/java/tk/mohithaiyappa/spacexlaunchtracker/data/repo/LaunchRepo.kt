package tk.mohithaiyappa.spacexlaunchtracker.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response.LaunchItem
import tk.mohithaiyappa.spacexlaunchtracker.data.room.dao.LaunchDao
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity
import tk.mohithaiyappa.spacexlaunchtracker.data.source.remote.LaunchService
import tk.mohithaiyappa.spacexlaunchtracker.util.NetworkResponse
import javax.inject.Inject

class LaunchRepo
    @Inject
    constructor(
        private val launchService: LaunchService,
        private val launchDao: LaunchDao,
    ) {
        suspend fun syncLaunches(): NetworkResponse<List<LaunchItem>> {
            return withContext(Dispatchers.IO) {
                try {
                    val launches = launchService.getLaunches()
                    launches
                        .map(LaunchItem::toLaunchEntity)
                        .forEach(launchDao::insertLaunch)
                    NetworkResponse.Success(launches)
                } catch (e: Exception) {
                    NetworkResponse.Failure(e.message ?: "An unknown error occurred")
                }
            }
        }

        suspend fun getLaunches(): Flow<List<LaunchEntity>> {
            return withContext(Dispatchers.IO) {
                launchDao.getAllLaunches()
            }
        }

        suspend fun search(query: String): Flow<List<LaunchEntity>> {
            return withContext(Dispatchers.IO) {
                launchDao.searchLaunches(query)
            }
        }
    }
