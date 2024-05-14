package com.mokshith.mvvmclean.data.remote

import com.mokshith.mvvmclean.data.remote.dto.coins.CoinDetailsDto
import com.mokshith.mvvmclean.data.remote.dto.coins.CoinDto
import com.mokshith.mvvmclean.data.remote.dto.harryPotter.HPModelDto
import com.mokshith.mvvmclean.data.remote.dto.menu.MenuListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServices {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetailsById(@Path("coinsId") coinId: String): CoinDetailsDto

    @GET("characters")
    suspend fun getHpList(): List<HPModelDto>


    @GET("character/{id}")
    suspend fun getHpDetails(@Path("id") id: String): List<HPModelDto>


    @GET("categories.php")
    suspend fun getMenuList(): Response<MenuListDto>

}