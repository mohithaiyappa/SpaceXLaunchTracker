package tk.mohithaiyappa.spacexlaunchtracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launch")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunch(launch: LaunchEntity)

    @Delete
    fun deleteLaunch(launch: LaunchEntity)

    @Query("SELECT * FROM launch WHERE missionName LIKE :query OR launchYear LIKE :query OR rocketName LIKE :query")
    fun searchLaunches(query: String): Flow<List<LaunchEntity>>

    @Query("SELECT * FROM launch WHERE flightNumber = :flightNumber")
    fun getLaunchByFlightNumber(flightNumber: Int): Flow<LaunchEntity>
}
