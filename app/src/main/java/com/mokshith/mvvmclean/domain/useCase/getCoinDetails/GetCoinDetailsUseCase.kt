package com.mokshith.mvvmclean.domain.useCase.getCoinDetails

import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.coins.getCoinDetails
import com.mokshith.mvvmclean.domain.models.CoinDetails
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<ApiState<CoinDetails>> = flow {
        try {
            emit(ApiState.Loading())
            val coinDetails = repository.getCoinDetailsById(coinId).getCoinDetails()
            emit(ApiState.Success(coinDetails))
        } catch (e: IOException) {
            // it invokes when our api could not connect to remote api
            //i.e if there is not internet connection
            emit(ApiState.Error("Couldn't reach servers. Check your internet connection"))
        }
    }
}