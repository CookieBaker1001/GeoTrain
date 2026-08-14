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
                .padding(WindowInsets.systemBars.asPaddingValues()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { NavigationController.navigate("HomeScreen")},
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "<")
                }
            }

            //Spacer(modifier = Modifier.padding(8.dp).weight(3f, true))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val phrase by gameScreenVM.phrase.collectAsState()
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = phrase,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    var text by remember { mutableStateOf("") }
                    val country by gameScreenVM.currentCountry.collectAsState();
                    val current by gameScreenVM.current.collectAsState();
                    val score by gameScreenVM.score.collectAsState();
                    val max by gameScreenVM.max.collectAsState();

                    Text(modifier = Modifier
                        .weight(2f, true),
                        text = "Flag ${current+1} out of ${max}")

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

                    Spacer(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp))

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
                        ),
                        modifier = Modifier
                            .weight(1f, true),
                    ) {
                        Text(text = "Check")
                    }

                    Spacer(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp))

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
            }

            //Spacer(modifier = Modifier.padding(vertical = 48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp).weight(3f, true))
            }
        }
    }
}