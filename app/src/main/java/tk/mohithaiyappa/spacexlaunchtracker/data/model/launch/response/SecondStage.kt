package tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response

import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("block")
    val block: Int?,
    @SerializedName("payloads")
    val payloads: List<Payload?>?,
)
