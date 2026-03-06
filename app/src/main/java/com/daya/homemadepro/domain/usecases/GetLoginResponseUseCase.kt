package com.daya.homemadepro.domain.usecases

import com.daya.homemadepro.data.modal.Data
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.data.repository.LoginRepoImpl
import com.daya.homemadepro.domain.model.DomainLoginResponse
import com.daya.homemadepro.domain.repository.LoginResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class GetLoginResponseUseCase {

    private val repository : LoginResponseRepository by lazy { LoginRepoImpl() }

    operator fun invoke(reuest: LoginRequestEncrypted) = flow<Result<LoginRequestX>> {
        val response = repository.getLoginResponse(reuest)
        emit(response)
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}