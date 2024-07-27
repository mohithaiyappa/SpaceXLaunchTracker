package tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response

import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: String?,
)
