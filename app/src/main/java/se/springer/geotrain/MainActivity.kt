package se.springer.geotrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import se.springer.geotrain.components.NavigationController
import se.springer.geotrain.ui.screens.HomeScreen
import se.springer.geotrain.ui.theme.GeoTrainTheme
import se.springer.geotrain.ui.viewmodels.HomeScreenVMFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import se.springer.geotrain.ui.screens.EndGameScreen
import se.springer.geotrain.ui.screens.GameScreen
import se.springer.geotrain.ui.screens.SettingsScreen
import se.springer.geotrain.ui.viewmodels.EndGameScreenVM
import se.springer.geotrain.ui.viewmodels.EndGameScreenVMFactory
import se.springer.geotrain.ui.viewmodels.GameScreenVM
import se.springer.geotrain.ui.viewmodels.GameScreenVMFactory
import se.springer.geotrain.ui.viewmodels.HomeScreenVM
import se.springer.geotrain.ui.viewmodels.SettingsScreenVM
import se.springer.geotrain.ui.viewmodels.SettingsScreenVMFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoTrainTheme {
                MyApp(
                    viewModel(factory = HomeScreenVMFactory(application)),
                    viewModel(factory = SettingsScreenVMFactory(application)),
                    viewModel(factory = GameScreenVMFactory(application)),
                    viewModel(factory = EndGameScreenVMFactory(application))
                )
            }
        }
    }
}

@Composable
fun MyApp(
    homeScreenVM: HomeScreenVM,
    settingsScreenVM: SettingsScreenVM,
    gameScreenVM: GameScreenVM,
    endGameScreenVM: EndGameScreenVM
) {
    val navController = rememberNavController()
    NavigationController.setNavController(navController)

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(homeScreenVM = homeScreenVM)
        }
        composable("SettingsScreen") {
            SettingsScreen(settingsScreenVM = settingsScreenVM)
        }
        composable("GameScreen") {
            GameScreen(gameScreenVM = gameScreenVM)
        }
        composable("EndGameScreen") {
            EndGameScreen(endGameScreenVM = endGameScreenVM)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoTrainTheme {
        Greeting("Android")
    }
}