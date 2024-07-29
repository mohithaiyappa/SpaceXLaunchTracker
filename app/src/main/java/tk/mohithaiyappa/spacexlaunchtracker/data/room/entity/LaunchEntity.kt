package tk.mohithaiyappa.spacexlaunchtracker.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launch")
data class LaunchEntity(
    @PrimaryKey(autoGenerate = false)
    val flightNumber: Int,
    val missionName: String,
    val launchYear: String,
    val rocketName: String,
    val missionPatch: String,
    val missionPatchSmall: String,
    val rocketType: String,
    val payloadDetails: String,
    val launchSite: String,
)
