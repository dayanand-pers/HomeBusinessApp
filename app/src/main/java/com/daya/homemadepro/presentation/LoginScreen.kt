package com.daya.homemadepro.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daya.homemadepro.R
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryCodePicker

@Composable
fun LoginScreen(viewModel: LoginViewModel) {



    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val uiStates by viewModel.uiStates.collectAsStateWithLifecycle()


    var  query by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Login", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(id = R.drawable.ic_secure_logo),
            contentDescription = "Login logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row( modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start) {


            CountryCodePicker(modifier = Modifier.width(100.dp), selectedCountry = Country.India, onCountrySelected = {})
            OutlinedTextField(value = email, onValueChange = {
                email = it
            }, label = {
                Text("Enter phone")
            })

        }



        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text("Enter password")
        }, visualTransformation = PasswordVisualTransformation())


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            Log.e("Tag ", "Email : $email and password : $password")
            viewModel.updateUserNameAndPassword(email, password)


        }) {
            if (uiStates.isLoading){
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }else{
                Text(text = "Login now")
            }

            /*if (uiStates.error != ""){

                Log.e("Tag", "Error code "+uiStates.error)
            }*/

            if (uiStates.error.isEmpty() && uiStates.data != null){

                Log.d("API_SUCCESS", "User: "+uiStates.data.toString())

                /*val intentHome = Intent(mContext, HomeActivity::class.java)
                startActivity(mContext, intentHome, null)*/

            }else{
                Log.d("API_ERROR", "Error: "+uiStates.error)
            }
//            Textxt(text = "Login now")
        }


        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(modifier = Modifier.padding( end = 20.dp) , onClick = {}) {
                Text(fontSize = 20.sp, text = "Forgot password?")
            }
        }



    }

}


@Composable
fun CountryCodePicker(){

}