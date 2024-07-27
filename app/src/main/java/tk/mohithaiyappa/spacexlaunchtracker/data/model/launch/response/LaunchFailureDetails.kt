package tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response

import com.google.gson.annotations.SerializedName

data class LaunchFailureDetails(
    @SerializedName("altitude")
    val altitude: Int?,
    @SerializedName("reason")
    val reason: String?,
    @SerializedName("time")
    val time: Int?,
)
