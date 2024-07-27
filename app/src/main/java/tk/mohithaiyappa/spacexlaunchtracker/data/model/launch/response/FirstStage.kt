package tk.mohithaiyappa.spacexlaunchtracker.data.model.launch.response

import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("cores")
    val cores: List<Core?>?,
)
