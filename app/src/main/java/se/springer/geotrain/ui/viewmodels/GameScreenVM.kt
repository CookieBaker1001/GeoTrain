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
import se.springer.geotrain.components.countries

class GameScreenVM (
    application: Application
) : AndroidViewModel(application) {
    private val _running = MutableStateFlow(false)
    val running: StateFlow<Boolean> = _running

    //var currentCountry = GeoTrainLogic.currentCountry
    val currentCountry: StateFlow<Country> = GeoTrainLogic.currentCountry

    var _startTime: Long = 0L

    val max: StateFlow<Int> = GeoTrainLogic.maxCount
    val score: StateFlow<Int> = GeoTrainLogic.score
    val current: StateFlow<Int> = GeoTrainLogic.index
    fun startGame() {
        Log.d("Logger", "Started game!");
        _running.value = true;
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
        GeoTrainLogic.guess(text)
        Log.d("Logger", "Typed answer: ${text}");
    }
}