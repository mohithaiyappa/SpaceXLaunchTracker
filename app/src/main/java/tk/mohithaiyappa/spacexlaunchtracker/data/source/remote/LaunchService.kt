package tk.mohithaiyappa.spacexlaunchtracker.data.source.remote

import retrofit2.http.GET
import tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response.LaunchItem

interface LaunchService {
    @GET("v3/launches")
    suspend fun getLaunches(): List<LaunchItem>
}
