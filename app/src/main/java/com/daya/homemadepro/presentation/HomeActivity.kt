package com.daya.homemadepro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val imagePostViewModel: PostImageViewModel by viewModels()
    private val imageViewModel: ImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            loadImahePagination()
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun loadImaheListUI() {
        val imageUiState by imagePostViewModel.uiState.collectAsStateWithLifecycle()
        var rememberQuery by rememberSaveable { mutableStateOf("") }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Search.... ") },
                    value = rememberQuery, onValueChange = {
                        rememberQuery = it
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }) { innerPadding ->

            if (imageUiState.isLoading) {
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            imageUiState.successdata?.let { list ->
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    items(list.hits) { hit ->
                        GlideImage(
                            model = hit.largeImageURL,
                            contentDescription = hit.likes.toString(),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun loadImahePagination() {
        val imagesFromAPI = imageViewModel.getImages("Yellow").collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = imagesFromAPI,
                key = { it.id }
            ) { item ->
                item?.let { hit ->

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {


                        GlideImage(
                            model = hit.largeImageURL,
                            contentDescription = hit.likes.toString(),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                                .padding(10.dp)
                        )

                        Row(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(70.dp)) {
                            Text(text = "Likes "+hit.likes.toString(), fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.background(color = Color.Black.copy(alpha = 0.5f))
                                    .padding(5.dp).height(40.dp))
                            Text(text = "Views "+hit.views.toString(), fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.background(color = Color.Black.copy(alpha = 0.5f)).padding(5.dp).height(40.dp))
                        }
                    }
                    HorizontalDivider()
                }
            }

            when (val state = imagesFromAPI.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is LoadState.Error -> {
                    // Handle error
                }
                else -> {}
            }
        }
    }
}
