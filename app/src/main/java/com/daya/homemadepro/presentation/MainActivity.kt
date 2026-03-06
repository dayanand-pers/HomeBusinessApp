package com.daya.homemadepro.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainActivity : ComponentActivity() {



    private val viewModel : LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*lifecycleScope.launch {

            viewModel.sharedflow.collectLatest{
                Log.e("TAG", "Inside shared flow $it")
            }
        }

        lifecycleScope.launch {

            delay(4000)
            viewModel.sharedflow.collectLatest{
                Log.e("TAG", "Inside shared flow $it")
            }
        }*/

        lifecycleScope.launch {

//            delay(4000)
            viewModel.myststeFlow.collectLatest{
                Log.e("TAG", "Inside state flow $it")
            }
        }

        setContent {


            var mContext : Context = this;
            /*runBlocking {
                launch {
                    setValueCoroutineinMain()
                }
            }*/

//            setValueCoroutineinMain() // The coroutine suspends here, freeing the main thread
                 // Resumes on main thread after fetch

            /*LaunchedEffect(
                "abc"
            ) {
                setValueCoroutineinMain()
            }*/

            withoutCoRoutine()

            LoginScreen(viewModel)

            /*HomeMadeProTheme {

                val uiStates by viewModel.uiStates.collectAsStateWithLifecycle()


                var  query by rememberSaveable { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TextField(value = query)
                }) { innerPadding ->

                }
            }*/
        }


    }

    suspend fun setValueCoroutineinMain(){
        withContext(Dispatchers.IO){
            Log.e("TAG", "Inside suspend coroutine from Activity")
            delay(4000)
            Log.e("TAG", "Inside suspend after coroutine from Activity")
        }
    }

    fun withoutCoRoutine(){
        Log.e("TAG", "Inside without coroutine from Activity")

    }

}

