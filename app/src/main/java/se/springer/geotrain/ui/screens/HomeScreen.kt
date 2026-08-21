package se.springer.geotrain.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.theme.CustomButtonColor
import se.springer.geotrain.ui.theme.CustomGradientBackground
import se.springer.geotrain.ui.viewmodels.HomeScreenVM

@Composable
fun HomeScreen (
    homeScreenVM: HomeScreenVM
) {
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(4f, true))
            {}

            // Title part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Geo Trainer",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            // Description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f, true),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Practice your geography skills. Pick a world part and try to identify as many countries from their flags as possible.",
                    textAlign = TextAlign.Center,
                )
            }

            // Button element
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val worldPart by homeScreenVM.worldPart.collectAsState()
                Button(
                    onClick = {
                        NavigationController.navigate("SettingsScreen")
                    },
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "Play")
                }
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Text(text = "World part: $worldPart")
//                    Button(
//                        onClick = {
//                            NavigationController.navigate("SettingsScreen")
//                        },
//                        colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
//                    ) {
//                        Text(text = "Play")
//                    }
//                }
            }

            // Spacer
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(6f, true))
            {}

            // Footer part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "KnaKoBraK AB - CR - TM",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}