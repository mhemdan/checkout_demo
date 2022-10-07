package com.hemdan.presentation.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hemdan.presentation.ui.cardsubmit.CardSubmitScreen
import com.hemdan.presentation.ui.cardverify.CardVerifyScreen
import com.hemdan.presentation.ui.submutresult.CardSubmitResultScreen
import com.hemdan.presentation.ui.theme.CheckOutTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckOutTheme {
                val navController = rememberNavController()
                BackHandler(enabled = true, onBack = {
                    navController.navigateUp()
                })
                NavHost(navController = navController, startDestination = "cardSubmit") {
                    composable("cardSubmit") {
                        CardSubmitScreen(onNavigateToVerify = { url ->
                            Log.d("Navigation#", "1")
                            navController.navigate(
                                "cardVerify?url=$url"
                            )
                        })
                    }
                    composable("cardVerify?url={urlValue}") { backStackEntry ->
                        backStackEntry.arguments?.getString("urlValue")
                            ?.let {
                                CardVerifyScreen(
                                    url = it,
                                    onNavigateToSubmitResult = { isSubmittedSuccessfully ->
                                        Log.d("Navigation#", "2")
                                        navController.navigate(
                                            route = "cardSubmitResult?isSuccess=$isSubmittedSuccessfully"
                                        ) {
                                            popUpTo(route = "cardSubmit") {
                                                inclusive = true
                                            }
                                        }

                                    })
                            }
                    }

                    composable(
                        "cardSubmitResult?isSuccess={isSuccessValue}",
                        arguments = listOf(navArgument("isSuccessValue") {
                            type = NavType.BoolType
                        }
                        )
                    ) { backStackEntry ->

                        backStackEntry.arguments?.getBoolean("isSuccessValue")
                            ?.let {
                                CardSubmitResultScreen(it)
                            }
                    }
                }

            }
        }
    }
}

