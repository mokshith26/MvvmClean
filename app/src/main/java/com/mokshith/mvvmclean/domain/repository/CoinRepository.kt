package com.mokshith.mvvmclean.domain.repository

import com.mokshith.mvvmclean.data.remote.dto.coins.CoinDetailsDto
import com.mokshith.mvvmclean.data.remote.dto.coins.CoinDto
import com.mokshith.mvvmclean.data.remote.dto.harryPotter.HPModelDto
import com.mokshith.mvvmclean.data.remote.dto.menu.MenuListDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CoinRepository {

    //in the domain layer we can define our repository
    //if you what to test use cases later

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetailsById(coinId: String): CoinDetailsDto

    suspend fun getHPList(): List<HPModelDto>

    suspend fun getHPDetails(id: String): List<HPModelDto>

    suspend fun getMenuList(): Response<MenuListDto>

}


