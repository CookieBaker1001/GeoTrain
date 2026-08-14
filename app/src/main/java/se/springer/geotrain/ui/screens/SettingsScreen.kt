package se.springer.geotrain.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.components.WorldPart
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
                .padding(WindowInsets.systemBars.asPaddingValues()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Settings Screen",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f, true),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var expanded by remember { mutableStateOf(false) }
                var selectedWordPart by remember { mutableStateOf(settingsScreenVM.mode.value) }

                Text(text = "World part: $selectedWordPart")

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {

                    Button(
                        onClick = { expanded = true },
                        colors = ButtonColors(Color.DarkGray, Color.White, Color.Red, Color.Red)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
    //                        Image(
    //                            painter = painterResource(id = selected.flagRes),
    //                            contentDescription = selected.name,
    //                            modifier = Modifier.size(24.dp)
    //                        )
                            Text(text = "Choose")
    //                        Spacer(modifier = Modifier.width(8.dp))
    //                        Text(selected.code.uppercase())
                        }
                    }

    //                TextField(
    //                    value = selectedWordPart.toString(),
    //                    onValueChange = {},
    //                    readOnly = true,
    //                    label = { Text(text = "World part") },
    //                    trailingIcon = {
    //                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
    //                    },
    //                    modifier = Modifier
    //                        .fillMaxWidth(),
    //                )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(192.dp)
                    ) {
                        WorldPart.entries.forEach { part ->
                            DropdownMenuItem(
                                text = { Text(part.name) },
                                onClick = {
                                    selectedWordPart = part
                                    settingsScreenVM.setMode(part.ordinal)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(5f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        NavigationController.navigate("HomeScreen")
                    }
                ) {
                    Text(text = "Home")
                }
            }
        }
    }
}