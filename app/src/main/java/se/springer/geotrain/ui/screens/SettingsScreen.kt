package se.springer.geotrain.ui.screens

import android.R.attr.onClick
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.viewmodels.SettingsScreenVM

@Composable
fun SettingsScreen (
    settingsScreenVM: SettingsScreenVM
) {
    Row(
        modifier = Modifier.
            background(color = Color(0.5f, 0.5f, 0.5f))
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