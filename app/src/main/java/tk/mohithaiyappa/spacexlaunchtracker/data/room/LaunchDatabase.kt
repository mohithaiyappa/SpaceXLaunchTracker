package tk.mohithaiyappa.spacexlaunchtracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.mohithaiyappa.spacexlaunchtracker.data.room.dao.LaunchDao
import tk.mohithaiyappa.spacexlaunchtracker.data.room.entity.LaunchEntity

@Database
(entities = [LaunchEntity::class], version = 2)
abstract class LaunchDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}
