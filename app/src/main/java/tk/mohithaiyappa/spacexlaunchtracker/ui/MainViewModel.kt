package tk.mohithaiyappa.spacexlaunchtracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tk.mohithaiyappa.spacexlaunchtracker.data.repo.LaunchRepo
import tk.mohithaiyappa.spacexlaunchtracker.util.NetworkResponse
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val launchRepo: LaunchRepo,
    ) : ViewModel() {
        var searchQuery = ""
            private set

        fun syncLaunches() {
            viewModelScope.launch {
                val result = launchRepo.syncLaunches()
                when (result) {
                    is NetworkResponse.Success -> {
                    }
                    is NetworkResponse.Failure -> {
                    }
                }
            }
        }

        suspend fun getlaunches() = launchRepo.getLaunches()

        suspend fun searchLaunches(query: String) =
            launchRepo.search(query).apply {
                searchQuery = query
            }

        suspend fun getLaunch(flightNumber: Int) = launchRepo.getLaunch(flightNumber)
    }
