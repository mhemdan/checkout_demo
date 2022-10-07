package com.hemdan.presentation.ui.cardsubmit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hemdan.presentation.R
import com.hemdan.presentation.ui.utils.CardNumberMask
import com.hemdan.presentation.ui.utils.ExpirationDateMask
import androidx.hilt.navigation.compose.hiltViewModel
import com.hemdan.presentation.ui.utils.DotsLoadingAnimation

@Composable
fun CardSubmitScreen(
    onNavigateToVerify: (url: String) -> Unit,
    viewModel: CardSubmitViewModel = hiltViewModel()
) {
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    val viewState by viewModel.viewState.collectAsState()
    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = CardNumberMask(),
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = {
                    Text(text = stringResource(R.string.app_card_number))
                })

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.weight(1.0f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    visualTransformation = ExpirationDateMask(),
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = {
                        Text(text = stringResource(R.string.app_card_expiry_date))
                    })

                OutlinedTextField(
                    modifier = Modifier.weight(1.0f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    value = cvv,
                    onValueChange = {
                        cvv = it.take(3)
                    },
                    label = {
                        Text(text = stringResource(R.string.app_card_cvv))
                    })
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (viewState !is CardSubmitViewModel.ViewState.Loading)
                        viewModel.verifyCard(
                            cardNumber = cardNumber,
                            expiryDate = expiryDate,
                            cvv = cvv
                        )
                }) {
                if (viewState is CardSubmitViewModel.ViewState.Loading) {
                    DotsLoadingAnimation()
                } else {
                    Text(text = stringResource(R.string.app_pay))
                }
            }
        }
    }

    LaunchedEffect(viewState) {
        when (viewState) {
            is CardSubmitViewModel.ViewState.Success -> {
                onNavigateToVerify((viewState as CardSubmitViewModel.ViewState.Success).url)
            }
            is CardSubmitViewModel.ViewState.Error -> {}
            else -> {
//    no-op
            }

        }
    }
}
