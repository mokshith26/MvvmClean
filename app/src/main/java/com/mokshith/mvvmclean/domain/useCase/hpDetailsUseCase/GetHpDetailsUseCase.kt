package com.mokshith.mvvmclean.domain.useCase.hpDetailsUseCase

import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.getHpDetails
import com.mokshith.mvvmclean.domain.models.HPModelDetails
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetHpDetailsUseCase @Inject constructor(
    val repository: CoinRepository
) {
    operator fun invoke(): Flow<ApiState<List<HPModelDetails>>> = flow {
        try {
            emit(ApiState.Loading())
            val hpDetails = repository.getHPDetails("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8").map { it.getHpDetails() }
            emit(ApiState.Success(hpDetails))
        } catch (e: IOException) {
            // it invokes when our api could not connect to remote api
            // i.e if there is not internet connection
            emit(ApiState.Error("Couldn't reach servers. Check your internet connection"))
        }
    }
}