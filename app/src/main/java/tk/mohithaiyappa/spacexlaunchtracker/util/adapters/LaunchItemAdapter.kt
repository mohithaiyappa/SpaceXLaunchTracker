package tk.mohithaiyappa.spacexlaunchtracker.util.adapters

import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity
import tk.mohithaiyappa.spacexlaunchtracker.databinding.ViewLaunchItemBinding

class LaunchItemAdapter() : ListAdapter<LaunchEntity, LaunchItemAdapter.LaunchItemViewHolder>(callback) {
    class LaunchItemViewHolder(val binding: ViewLaunchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LaunchItemViewHolder {
        val binding = ViewLaunchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LaunchItemViewHolder,
        position: Int,
    ) {
        val launchEntity = getItem(position)
        holder.binding.apply {
            ivMissionPatch.load(launchEntity.missionPatchSmall) {
                listener(
                    onError = { request, result ->
                    },
                )
            }
            tvLaunchYear.text = launchEntity.launchYear
            tvRocketName.text = launchEntity.rocketName
            tvMissionName.text = launchEntity.missionName
            tvMissionName.movementMethod = LinkMovementMethod.getInstance()
            tvMissionName.ellipsize = TextUtils.TruncateAt.MARQUEE
            tvMissionName.isSelected = true
            tvMissionName.isSingleLine = true
        }
    }

    companion object {
        val callback =
            object : DiffUtil.ItemCallback<LaunchEntity>() {
                override fun areItemsTheSame(
                    oldItem: LaunchEntity,
                    newItem: LaunchEntity,
                ): Boolean {
                    return oldItem.flightNumber == newItem.flightNumber
                }

                override fun areContentsTheSame(
                    oldItem: LaunchEntity,
                    newItem: LaunchEntity,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
