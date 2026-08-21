package se.springer.geotrain.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.springer.geotrain.Greeting
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.theme.CustomButtonColor
import se.springer.geotrain.ui.theme.CustomGradientBackground
import se.springer.geotrain.ui.theme.GeoTrainTheme
import se.springer.geotrain.ui.viewmodels.GameScreenVM
import se.springer.geotrain.ui.viewmodels.GameScreenVMFactory

@Composable
fun GameScreen (
    gameScreenVM: GameScreenVM
) {
    LaunchedEffect(true) {
        gameScreenVM.startGame()
    }

    Row(
        modifier = Modifier.
        background(CustomGradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f, true),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { NavigationController.navigate("HomeScreen")},
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "<")
                }
            }

            // Spacer
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(6f, true))
            {}

            // Title part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val phrase by gameScreenVM.phrase.collectAsState()
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = phrase,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            // Spacer
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(2f, true))
            {}

            var text by remember { mutableStateOf("") }
            val country by gameScreenVM.currentCountry.collectAsState();
            val current by gameScreenVM.current.collectAsState();
            val score by gameScreenVM.score.collectAsState();
            val max by gameScreenVM.max.collectAsState();

            // Progression part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(2f, true),
                    text = "Flag ${current+1} out of ${max}",
                    textAlign = TextAlign.Center
                )
            }

            // Icon part
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(6f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (country.name[0] != "") {
                    Icon(
                        painter = painterResource(id = country.flagRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .weight(4f, true),
                    )
                }
            }

            // Check button
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        Log.d("Logger", "Current: ${current}, Score is: ${score} / ${max}");
                        gameScreenVM.guess(text)
                        if (current >= (max - 1)) {
                            NavigationController.navigate("EndGameScreen");
                        }
                        text = ""
                    },
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red
                    )
                ) {
                    Text(text = "Check")
                }
            }

            // Input field
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(4f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium
                        )
                        .weight(1f, true),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Name") }
                )
            }

            // Footer part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    text = "KnaKoBraK AB - CR - TM",
//                    textAlign = TextAlign.Center,
//                )
            }
        }
    }
}