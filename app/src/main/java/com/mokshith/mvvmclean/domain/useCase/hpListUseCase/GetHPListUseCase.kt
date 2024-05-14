package com.mokshith.mvvmclean.domain.useCase.hpListUseCase

import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.harryPotter.getHpList
import com.mokshith.mvvmclean.domain.models.HPModel
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetHPListUseCase @Inject constructor(
    val repository: CoinRepository
) {

    operator fun invoke(): Flow<ApiState<List<HPModel>>> = flow {
        try {
            emit(ApiState.Loading())
            val hpList = repository.getHPList().map { it.getHpList() }
            emit(ApiState.Success(hpList))
        } catch (e: IOException) {
            // it invokes when our api could not connect to remote api
            // i.e if there is not internet connection
            emit(ApiState.Error("Couldn't reach servers. Check your internet connection"))
        }
    }
}