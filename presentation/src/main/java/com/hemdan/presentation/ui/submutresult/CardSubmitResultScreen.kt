package com.hemdan.presentation.ui.submutresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hemdan.presentation.R

@Composable
fun CardSubmitResultScreen(isSubmittedSuccessfully: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (isSubmittedSuccessfully) R.drawable.success else R.drawable.failure),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = if (isSubmittedSuccessfully) R.string.app_payment_success else R.string.app_payment_failed)
        )
    }
}