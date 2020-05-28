package com.example.cropdigital.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://guarded-plateau-51199.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val interceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}
private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface RepositoryApiService {
    @GET("/registro")
    fun getItems():
            Deferred<List<ItemsResponse>>

    @GET("/registro/{index}")
    fun getIndex(@Path("index") index: Int):
            Deferred<ItemsResponse>

    @Headers("Content-Type: application/json")
    @POST("/registro")
    fun addItems(@Body itemRequest: ItemsRequest):
            Deferred<ItemsResponse>
}

object RepositoryApi {
    val retrofitService: RepositoryApiService by lazy {
        retrofit.create(RepositoryApiService::class.java)
    }
}