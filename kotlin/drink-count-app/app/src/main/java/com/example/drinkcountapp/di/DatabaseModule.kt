//package com.example.drinkcountapp.di
//
//import android.content.Context
//import com.example.drinkcountapp.data.AppDatabase
//import com.example.drinkcountapp.data.DrinkDao
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Singleton
//
//@InstallIn(ApplicationComponent::class)
//@Module
//class DatabaseModule {
//
//    @Singleton
//    @Provides
//    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
//        return AppDatabase.getInstance(context)
//    }
//
//    @Provides
//    fun provideDrinkDao(appDatabase: AppDatabase): DrinkDao {
//        return appDatabase.drinkDao()
//    }
//
//}