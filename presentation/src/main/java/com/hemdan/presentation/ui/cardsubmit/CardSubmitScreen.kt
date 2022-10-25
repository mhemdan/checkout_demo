package com.hemdan.presentation.ui.cardsubmit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hemdan.presentation.R
import com.hemdan.presentation.ui.utils.CardNumberMask
import com.hemdan.presentation.ui.utils.DotsLoadingAnimation
import com.hemdan.presentation.ui.utils.ExpirationDateMask
import com.hemdan.presentation.ui.utils.Visa

@Composable
fun CardSubmitScreen(
    onNavigateToVerify: (url: String) -> Unit,
    viewModel: CardSubmitViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var expiryDate by remember { mutableStateOf(TextFieldValue("")) }
    var cvv by remember { mutableStateOf(TextFieldValue("")) }
    val viewState by viewModel.viewState.collectAsState()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val generalErrorValue = stringResource(id = R.string.general_error)
    val cardNumberError = stringResource(id = R.string.app_invalid_card_number_error)

    val cardType =
        if (viewState is CardSubmitViewModel.ViewState.CurrentCardType) {
            (viewState as CardSubmitViewModel.ViewState.CurrentCardType).card
        } else Visa()

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            Modifier
                .padding(paddingValues = it)
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    visualTransformation = CardNumberMask(cardType.mask),
                    value = cardNumber,
                    isError = viewState is CardSubmitViewModel.ViewState.CardNumberError,
                    onValueChange = { value ->
                        if (value.text.length == 2) {
                            viewModel.getCardType(value.text)
                        }
                        cardNumber = value.copy(value.text.take(cardType.digitCount))
                    },
                    label = {
                        Text(text = stringResource(R.string.app_card_number))
                    }
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1.0f),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        visualTransformation = ExpirationDateMask(),
                        value = expiryDate,
                        onValueChange = { value ->
                            expiryDate = if (value.text.length == 1 && value.text.toInt() > 1) {
                                val newValue = "0${value.text}"
                                value.copy(
                                    newValue,
                                    selection = TextRange(newValue.length)
                                )
                            } else {
                                value.copy(value.text.take(4))
                            }
                        },
                        label = {
                            Text(text = stringResource(R.string.app_card_expiry_date))
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.weight(1.0f),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        value = cvv,
                        onValueChange = { value ->
                            cvv = value.copy(value.text.take(cardType.cvvLength))
                        },
                        label = {
                            Text(text = stringResource(R.string.app_card_cvv))
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = cardNumber.text.isNotEmpty() && expiryDate.text.isNotEmpty() && cvv.text.isNotEmpty(),
                    onClick = {
                        focusManager.clearFocus()
                        if (viewState !is CardSubmitViewModel.ViewState.Loading) {
                            viewModel.submitCard(
                                cardNumber = cardNumber.text,
                                expiryDate = expiryDate.text,
                                cvv = cvv.text
                            )
                        }
                    }
                ) {
                    if (viewState is CardSubmitViewModel.ViewState.Loading) {
                        DotsLoadingAnimation()
                    } else {
                        Text(text = stringResource(R.string.app_pay))
                    }
                }
            }
        }
    }

    LaunchedEffect(viewState) {
        when (viewState) {
            is CardSubmitViewModel.ViewState.Success -> {
                onNavigateToVerify((viewState as CardSubmitViewModel.ViewState.Success).url)
            }
            is CardSubmitViewModel.ViewState.SubmitError -> {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = generalErrorValue
                )
            }
            is CardSubmitViewModel.ViewState.CardNumberError -> {
                scaffoldState.snackbarHostState.showSnackbar(message = cardNumberError)
            }
            else -> {
//    no-op
            }
        }
    }
}
