package com.hemdan.presentation.ui.cardverify

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun CardVerifyScreen(
    url: String,
    onNavigateToSubmitResult: (isSuccess: Boolean) -> Unit,
    viewModel: CardVerifyViewModel = hiltViewModel()
) {
    val state = rememberWebViewState(url = url)
    val viewState by viewModel.viewState.collectAsState()
    WebView(state = state, onCreated = {
        it.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
    })

    LaunchedEffect(viewState) {
        when (viewState) {
            is CardVerifyViewModel.ViewState.NavigateToSuccessScreen -> {
                Log.d("Navigation#", "NavigateToSuccessScreen")
                onNavigateToSubmitResult(true)
            }
            is CardVerifyViewModel.ViewState.NavigateToFailureScreen -> {
                Log.d("Navigation#", "NavigateToFailureScreen")
                onNavigateToSubmitResult(false)
            }
            else -> {
                // no-op
            }
        }
    }

    LaunchedEffect(state.content.getCurrentUrl()) {
        Log.d("Navigation#", state.content.getCurrentUrl().toString())
        val currentUrl = state.content.getCurrentUrl()
        if (currentUrl != null && currentUrl != url) {
            viewModel.checkCallbackUrl(currentUrl)
        }
    }



    if (state.isLoading) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary
        )
    }


}