package com.example.skilllink.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.skilllink.data.local.managers.AppStoreManager
import com.example.skilllink.data.local.serializers.AppUser
import com.example.skilllink.data.local.serializers.AppStoreSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    // Providing App level datastore
    @Provides
    @Singleton
    fun providesAppStore(
        @ApplicationContext context: Context
    ): DataStore<AppUser> {
        return DataStoreFactory.create(
            serializer = AppStoreSerializer,
            produceFile = { File(context.filesDir, "app_store.pb") }
        )
    }

    @Provides
    @Singleton
    fun providesAppStoreManager(
        appStore: DataStore<AppUser>
    ): AppStoreManager {
        return AppStoreManager(appStore = appStore)
    }
}