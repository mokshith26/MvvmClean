package com.mokshith.mvvmclean.domain.useCase.menuListUseCase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.mokshith.mvvmclean.common.ApiState
import com.mokshith.mvvmclean.data.remote.dto.menu.getMenuList
import com.mokshith.mvvmclean.domain.models.MenuList
import com.mokshith.mvvmclean.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class GetMenuListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<ApiState<MenuList>> = flow {
        try {
            emit(ApiState.Loading())
            val menuList = repository.getMenuList().body()!!.getMenuList()
            val response = repository.getMenuList()
            if (response.isSuccessful) {
                emit(ApiState.Success(menuList))
            }else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                // Handle 404 Not Found specifically
                emit(ApiState.Error("Menu list not found. (404)"))
            } else {
                // Handle other errors (consider adding more specific checks)
                emit(ApiState.Error("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(ApiState.Error("Exception $e Couldn't reach servers. Check your internet connection"))
        } catch (e: TimeoutException){
            emit(ApiState.Error("Timeout Exception : $e"))
        }
    }
}