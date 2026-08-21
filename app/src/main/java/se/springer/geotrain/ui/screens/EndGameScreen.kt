package se.springer.geotrain.ui.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import se.springer.geotrain.components.GeoTrainLogic
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.theme.CustomButtonColor
import se.springer.geotrain.ui.theme.CustomGradientBackground
import se.springer.geotrain.ui.viewmodels.EndGameScreenVM

@Composable
fun EndGameScreen (
    endGameScreenVM: EndGameScreenVM
) {
    LaunchedEffect(true) {
        GeoTrainLogic.endGame();
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "The game is over!",
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
                val points by endGameScreenVM.score.collectAsState();
                val max by endGameScreenVM.max.collectAsState();
                val time by endGameScreenVM.time.collectAsState();
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "You scored",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = "${points} points",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = "out of ${max}",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Time taken ${time} seconds",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        NavigationController.navigate("HomeScreen")
                    },
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "Home")
                }
            }
        }
    }
}