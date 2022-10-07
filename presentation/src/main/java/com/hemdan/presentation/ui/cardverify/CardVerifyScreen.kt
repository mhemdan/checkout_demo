package com.hemdan.presentation.ui.cardverify

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

    Box(contentAlignment = Alignment.Center) {
        WebView(state = state, captureBackPresses = false, onCreated = {
            it.settings.apply {
                javaScriptEnabled = true
            }
        })

        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }

    LaunchedEffect(viewState) {
        when (viewState) {
            is CardVerifyViewModel.ViewState.NavigateToSuccessScreen -> {
                onNavigateToSubmitResult(true)
            }
            is CardVerifyViewModel.ViewState.NavigateToFailureScreen -> {
                onNavigateToSubmitResult(false)
            }
            else -> {
                // no-op
            }
        }
    }

    LaunchedEffect(state.content.getCurrentUrl()) {
        val currentUrl = state.content.getCurrentUrl()
        if (currentUrl != null && currentUrl != url) {
            viewModel.checkCallbackUrl(currentUrl)
        }
    }


}