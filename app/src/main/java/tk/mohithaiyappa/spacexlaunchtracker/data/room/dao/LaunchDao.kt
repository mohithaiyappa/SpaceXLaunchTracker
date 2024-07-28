package tk.mohithaiyappa.spacexlaunchtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launch")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Insert
    fun insertLaunch(launch: LaunchEntity)

    @Delete
    fun deleteLaunch(launch: LaunchEntity)
}
