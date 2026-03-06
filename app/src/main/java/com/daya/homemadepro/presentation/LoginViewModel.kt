package com.daya.homemadepro.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.domain.encryption.SecurityUtils
import com.daya.homemadepro.domain.usecases.GetLoginResponseUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.UUID

class LoginViewModel : ViewModel() {


    private val useCase : GetLoginResponseUseCase by lazy { GetLoginResponseUseCase() }

    val sharedflow = MutableSharedFlow<String>(
        replay = 4
    )

    private val _uiState = MutableStateFlow(UiStates())

    val myststeFlow = MutableStateFlow("");

    val uiStates = _uiState.asStateFlow()


    private val _query = MutableStateFlow("")


    fun getSharedFlow(){
        viewModelScope.launch {

            repeat(10) {
                delay(200)
                sharedflow.emit("Daya is sending from shared flow with $it")
            }


        }
    }

    fun getStateFlow(){
        viewModelScope.launch {

            repeat(10) {
                delay(500)
                myststeFlow.emit("Daya is sending Ststeflow with $it")
            }


        }
    }

    fun updateUserNameAndPassword(username : String, pass:String){

        val secUtils = SecurityUtils();

        var payload = createJsonFromLoginInfo(username, pass)

        var encrytedReq = secUtils.encryptData(payload.toString())

        _query.update { encrytedReq }

    }


    fun createJsonFromLoginInfo(username : String, pass:String) : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("clientKey", "e0d66c94cbc4de9f_"+ System.currentTimeMillis().toString())
        jsonObject.put("countryCode", "091")
        jsonObject.put("deviceId", getDeviceUniqueId())
        jsonObject.put("password", pass)
        jsonObject.put("phoneNumber", username)

        return jsonObject;
    }


    fun getDeviceUniqueId(): String {
        var deviceId = ""
        try {

            deviceId = UUID.randomUUID().toString()
            Log.e("Device id", "vvvvvvvvvvvvvvvvvvvv $deviceId")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return deviceId
    }



    init {


//        getSharedFlow()

        getStateFlow()

        viewModelScope.launch {


            getResp("Coroutine name 1")

            getResp("Coroutine name 2")

            getRespWithdelay("Coroutine name 3")

            _query.filter { it.isNotEmpty() }
                .collectLatest { query ->

                    val loginencryoted = LoginRequestEncrypted(query)
                    getaLoginResponse(loginencryoted)
                }



        }
    }

    fun getaLoginResponse(encryptedReq: LoginRequestEncrypted){
        useCase(encryptedReq).onStart { _uiState.update { UiStates(isLoading = true) } }
            .onEach {  result ->
                if (result.isSuccess){
                    _uiState.update { UiStates(data = result.getOrThrow()) }
                }else{
                    _uiState.update { UiStates(error = result.exceptionOrNull()?.message.toString()) }
                }
            }.catch { error->
                _uiState.update { UiStates(error = error.message.toString()) }
            }.launchIn(viewModelScope)
    }


    suspend fun getResp(name : String){
        Log.e("Coroutine", "Inside suspend coroutine $name")
    }

    suspend fun getRespWithdelay(name : String){
        Log.e("TAG", "Inside suspend before coroutine $name")
        delay(3000)
        Log.e("TAG", "Inside suspend after coroutine $name")
    }

}



data class UiStates(

    val isLoading: Boolean = false,
    val error: String = "",
    val data: LoginRequestX? = null
)



