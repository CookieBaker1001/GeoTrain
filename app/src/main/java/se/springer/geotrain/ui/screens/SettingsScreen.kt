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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.components.WorldPart
import se.springer.geotrain.ui.theme.CustomButtonColor
import se.springer.geotrain.ui.theme.CustomGradientBackground
import se.springer.geotrain.ui.viewmodels.SettingsScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen (
    settingsScreenVM: SettingsScreenVM
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

            // Title part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Game settings",
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
                    text = "Pick a world part from which flags will be drawn.",
                    textAlign = TextAlign.Center,
                )
            }

            // Dropdown element
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(4f, true),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var expanded by remember { mutableStateOf(false) }
                var selectedWordPart by remember { mutableStateOf(settingsScreenVM.mode.value) }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        Button(
                            onClick = { expanded = true },
                            colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "Choose")
                            }
                        }
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(192.dp)
                        ) {
                            WorldPart.entries.forEach { part ->
                                DropdownMenuItem(
                                    modifier = Modifier
                                        .height(40.dp),
                                    text = { Text(part.name) },
                                    onClick = {
                                        selectedWordPart = part
                                        settingsScreenVM.setWorldPart(part.ordinal)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Text(text = "World part: $selectedWordPart")
                }
            }


            // Start button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        settingsScreenVM.setGameMode(0)
                        NavigationController.navigate("GameScreen")
                    },
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "Start game!")
                }
            }

            // Spacer
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true))
            {}

            // Landscape game button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        settingsScreenVM.setGameMode(1)
                        NavigationController.navigate("GameScreen")
                    },
                    colors = ButtonColors(CustomButtonColor, Color.White, Color.Red, Color.Red)
                ) {
                    Text(text = "Landskpapsspelet")
                }
            }

            // Spacer
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f, true))
            {}

            // Footer part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
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