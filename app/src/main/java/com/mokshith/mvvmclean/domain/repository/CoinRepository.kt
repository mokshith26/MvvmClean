package com.mokshith.mvvmclean.domain.repository

import com.mokshith.mvvmclean.data.remote.dto.CoinDetailsDto
import com.mokshith.mvvmclean.data.remote.dto.CoinDto

interface CoinRepository {

    //in the domain layer we can define our repository
    //if you what to test use cases later

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetailsById(coinId: String): CoinDetailsDto
}


