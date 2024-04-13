package com.example.e_comget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comget.Datoum.GetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
//        val baseUrl = "http://192.168.43.10:3000"
        val baseUrl = "http://192.168.88.220:3000"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGlobalViewModel(): GlobalViewModel {
        return GlobalViewModel()
    }

    @Singleton
    @Provides
    fun provideGetApi(retrofit: Retrofit): GetApi {
        return retrofit.create(GetApi::class.java)
    }
}



