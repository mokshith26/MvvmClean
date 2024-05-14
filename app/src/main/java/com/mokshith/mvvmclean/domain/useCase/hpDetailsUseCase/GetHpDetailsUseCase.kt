package com.mokshith.mvvmclean.domain.useCase.hpDetailsUseCase

import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.harryPotter.getHpDetails
import com.mokshith.mvvmclean.domain.models.HPModelDetails
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetHpDetailsUseCase @Inject constructor(
    val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<ApiState<List<HPModelDetails>>> = flow {
        try {
            emit(ApiState.Loading())
            val hpDetails = repository.getHPDetails(id).map { it.getHpDetails() }
            emit(ApiState.Success(hpDetails))
        } catch (e: IOException) {
            // it invokes when our api could not connect to remote api
            // i.e if there is not internet connection
            emit(ApiState.Error("Couldn't reach servers. Check your internet connection"))
        }
    }
}