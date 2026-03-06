package com.daya.homemadepro.domain.repository

import com.daya.homemadepro.data.modal.Data
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.domain.model.DomainLoginResponse

interface LoginResponseRepository {

    suspend fun getLoginResponse(data : LoginRequestEncrypted) : Result<LoginRequestX>
}