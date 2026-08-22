package se.springer.geotrain.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import se.springer.geotrain.components.Country
import se.springer.geotrain.components.GeoTrainLogic
import se.springer.geotrain.components.Landscape
import se.springer.geotrain.components.QuizItem
import se.springer.geotrain.components.countries
import kotlin.random.Random

class GameScreenVM (
    application: Application
) : AndroidViewModel(application) {
    private val _running = MutableStateFlow(false)
    val running: StateFlow<Boolean> = _running

    //var currentCountry = GeoTrainLogic.currentCountry
//    val currentCountry: StateFlow<Country> = GeoTrainLogic.currentCountry
//    val currentLandscape: StateFlow<Landscape> = GeoTrainLogic.currentLandscape
    val currentItem: StateFlow<QuizItem?> = GeoTrainLogic.currentItem
//    val currentGameMode: StateFlow<Int> = GeoTrainLogic.gameMode

    var _startTime: Long = 0L

    val max: StateFlow<Int> = GeoTrainLogic.maxCount
    val score: StateFlow<Int> = GeoTrainLogic.score
    val current: StateFlow<Int> = GeoTrainLogic.index

    private val _phrase = MutableStateFlow<String>("");
    val phrase: StateFlow<String> get() = _phrase

    private val phrases = listOf("This one is tough.", "What about this one?", "Can you guess this one?",
        "This one is REALLY difficult.", "This one is literally unguessable.", "Too hard this one, eh?",
        "You'll never guess which country this is.", "But what is this one?", "I have no idea what this country is..")

    fun startGame() {
        Log.d("Logger", "Started game!");
        _running.value = true;
        _phrase.value = "Guess the flag!"
        _startTime = System.currentTimeMillis();
        GeoTrainLogic.startGame(_startTime);
//        loopJob = viewModelScope.launch {
//            while (isActive) {
//                delay(1000)
//                Log.d("Logger", "Job is running!")
//            }
//        }
    }

    fun stopGame() {
        _running.value = false;
//        loopJob?.cancel()
//        loopJob = null
        Log.d("Logger", "Time taken: " + (System.currentTimeMillis() - _startTime) + " ms");
        _startTime = 0L;
    }

    fun guess(text: String) {
        val randomIndex = Random.nextInt(0, phrases.size)
        _phrase.value = phrases[randomIndex]
        GeoTrainLogic.guess(text)
        Log.d("Logger", "Typed answer: ${text}");
    }
}