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
    }
