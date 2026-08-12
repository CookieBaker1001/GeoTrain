package se.springer.geotrain.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.R
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.viewmodels.GameScreenVM

@Composable
fun GameScreen (
    gameScreenVM: GameScreenVM
) {
    LaunchedEffect(true) {
        gameScreenVM.startGame()
    }

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, true),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Geo Trainer",
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

                    if (country.name != "") {
                        Icon(
                            painter = painterResource(id = country.flagRes),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(4.dp)
                        )
                    }

                    //Image(painter = painterResource(id = country.flagRes))

                    TextField(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = MaterialTheme.shapes.medium
                            ),
                        value = text,
                        onValueChange = { text = it },
                        label = { Text(text = "Name") }
                    )
                    Button(
                        onClick = {
                            gameScreenVM.guess(text)
                            text = ""
                        }
                    ) {
                        Text(text = "Check")
                    }
                }
            }
        }
    }
}