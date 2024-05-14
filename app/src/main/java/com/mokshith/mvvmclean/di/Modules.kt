package com.mokshith.mvvmclean.di
import com.mokshith.mvvmclean.common.ViewUtils.Companion.BASE_URL
import com.mokshith.mvvmclean.data.remote.WebServices
import com.mokshith.mvvmclean.data.repository.CoinRepositoryImpl
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
//the "SingletonComponent::class" is responsible for all the dependencies in the module
// lives as long as teh application does.
object Modules {
    @Provides
    // the function provides some thing so we use  @Provides
    @Singleton
    // the "@Singleton" make sures the it only single instance for hole application
    fun getRepository(webServices: WebServices): CoinRepository {
        return CoinRepositoryImpl(webServices)
    }
    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .build()
    }
    @Provides
    @Singleton
    fun getRetrofitInstance(okHttpClient: OkHttpClient): WebServices {
        return Retrofit.Builder()
            .baseUrl(
                BASE_URL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WebServices::class.java)
    }
}