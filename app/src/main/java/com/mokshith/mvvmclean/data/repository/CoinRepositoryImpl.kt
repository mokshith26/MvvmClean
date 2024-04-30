package com.mokshith.mvvmclean.data.repository

import com.mokshith.mvvmclean.data.remote.WebServices
import com.mokshith.mvvmclean.data.remote.dto.CoinDetailsDto
import com.mokshith.mvvmclean.data.remote.dto.CoinDto
import com.mokshith.mvvmclean.data.remote.dto.HPModelDto
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val webServices: WebServices
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return webServices.getCoins()
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsDto {
        return webServices.getCoinDetailsById(coinId = coinId)
    }

    override suspend fun getHPList(): List<HPModelDto> {
        return webServices.getHpList()
    }

    override suspend fun getHPDetails(id: String): List<HPModelDto> {
        return webServices.getHpDetails(id)
    }
}