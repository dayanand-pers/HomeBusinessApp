package com.daya.homemadepro.data.repository

import android.util.Log
import com.daya.homemadepro.data.modal.Data
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.data.modal.LoginResponse
import com.daya.homemadepro.data.remote.RetrofitInstance
import com.daya.homemadepro.domain.model.DomainLoginResponse
import com.daya.homemadepro.domain.repository.LoginResponseRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class LoginRepoImpl : LoginResponseRepository{

    private val apiService by lazy{ RetrofitInstance.getLoginApiService()}

    override suspend fun getLoginResponse(data: LoginRequestEncrypted): Result<LoginRequestX> {
         return try {

             /*var mutablelist = mutableListOf(1,2,3,3,);

             for (ml in mutablelist){
                 Log.e("TAG", "ML el $ml")


             }


             var loacdate = LocalDate.of(2022,2, 22)

             var loac2 = LocalDate.of(2023,2, 22)

             var days = ChronoUnit.DAYS.between(loacdate, loac2)
             val sunday = DAYS.SUNDAY
             Log.e("TAG", "Days are $days enum $sunday")


*/




            val response = apiService.loginToapplication(data) // {DomainLoginResponse(response = response.userDetails)}
            Result.success(response)

        }catch (e:Exception){
            Result.failure<LoginRequestX>(e)
        }
    }

    /*fun convToLoginDomain(loginresp : LoginResponse) : DomainLoginResponse{
        return DomainLoginResponse(loginresp);
    }*/
}


enum class DAYS(i: Int) {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3)
}