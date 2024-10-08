package tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response

import com.google.gson.annotations.SerializedName
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity
import tk.mohithaiyappa.spacexlaunchtracker.util.concatenateStrings

data class LaunchItem(
    @SerializedName("crew")
    val crew: List<Any?>?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("flight_number")
    val flightNumber: Int?,
    @SerializedName("is_tentative")
    val isTentative: Boolean?,
    @SerializedName("last_date_update")
    val lastDateUpdate: String?,
    @SerializedName("last_ll_launch_date")
    val lastLlLaunchDate: String?,
    @SerializedName("last_ll_update")
    val lastLlUpdate: String?,
    @SerializedName("last_wiki_launch_date")
    val lastWikiLaunchDate: String?,
    @SerializedName("last_wiki_revision")
    val lastWikiRevision: String?,
    @SerializedName("last_wiki_update")
    val lastWikiUpdate: String?,
    @SerializedName("launch_date_local")
    val launchDateLocal: String?,
    @SerializedName("launch_date_source")
    val launchDateSource: String?,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int?,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String?,
    @SerializedName("launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails?,
    @SerializedName("launch_site")
    val launchSite: LaunchSite?,
    @SerializedName("launch_success")
    val launchSuccess: Boolean?,
    @SerializedName("launch_window")
    val launchWindow: Int?,
    @SerializedName("launch_year")
    val launchYear: String?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("mission_id")
    val missionId: List<String?>?,
    @SerializedName("mission_name")
    val missionName: String?,
    @SerializedName("rocket")
    val rocket: Rocket?,
    @SerializedName("ships")
    val ships: List<String?>?,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int?,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String?,
    @SerializedName("tbd")
    val tbd: Boolean?,
    @SerializedName("telemetry")
    val telemetry: Telemetry?,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String?,
    @SerializedName("timeline")
    val timeline: Timeline?,
    @SerializedName("upcoming")
    val upcoming: Boolean?,
) {
    fun toLaunchEntity(): LaunchEntity {
        return LaunchEntity(
            flightNumber = this.flightNumber ?: 0,
            missionName = this.missionName ?: "",
            launchYear = this.launchYear ?: "",
            rocketName = this.rocket?.rocketName ?: "",
            missionPatch = this.links?.missionPatch ?: "",
            missionPatchSmall = this.links?.missionPatchSmall ?: "",
            rocketType = this.rocket?.rocketType ?: "",
            payloadDetails =
                this.rocket?.secondStage?.payloads?.concatenateStrings({ payload ->
                    return@concatenateStrings "${payload?.payloadType}, ${payload?.orbit}"
                }) ?: "",
            launchSite = this.launchSite?.siteName ?: "",
        )
    }
}
