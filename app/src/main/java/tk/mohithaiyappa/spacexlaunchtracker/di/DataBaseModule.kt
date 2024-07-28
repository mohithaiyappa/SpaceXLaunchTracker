package tk.mohithaiyappa.spacexlaunchtracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tk.mohithaiyappa.spacexlaunchtracker.data.room.LaunchDatabase
import tk.mohithaiyappa.spacexlaunchtracker.data.room.dao.LaunchDao

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideLaunchDataBase(
        @ApplicationContext context: Context,
    ): LaunchDatabase {
        return Room
            .databaseBuilder(
                context,
                LaunchDatabase::class.java,
                "launch-db",
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesLaunchDao(launchDatabase: LaunchDatabase): LaunchDao {
        return launchDatabase.launchDao()
    }
}
