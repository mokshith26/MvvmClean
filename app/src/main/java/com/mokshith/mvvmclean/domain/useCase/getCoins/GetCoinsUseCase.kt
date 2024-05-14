package com.mokshith.mvvmclean.domain.useCase.getCoins

import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.coins.toCoin
import com.mokshith.mvvmclean.domain.models.Coin
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

// Use case must have only one public function.
// only one use case in one class
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    //With the use of "operator fun invoke()" we can use "class GetCoinsUseCase" as " fun GetCoinsUseCase
    operator fun invoke(): Flow<ApiState<List<Coin>>> = flow {
        try {
            emit(ApiState.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(ApiState.Success(coins))
        } catch (e: IOException) {
            // it invokes when our api could not connect to remote api
            // i.e if there is not internet connection
            emit(ApiState.Error("Couldn't reach servers. Check your internet connection"))
        }
    }
}