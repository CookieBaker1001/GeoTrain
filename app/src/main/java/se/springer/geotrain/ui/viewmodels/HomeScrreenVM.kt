package se.springer.geotrain.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HomeScreenVM (
    application: Application
) : AndroidViewModel(application) {

    private val _running = MutableStateFlow(false)
    val running: StateFlow<Boolean> = _running

    private var loopJob: Job? = null

    var _startTime: Long = 0L

    fun startGame() {
        _running.value = true;
        _startTime = System.currentTimeMillis();
        loopJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                Log.d("Logger", "Job is running!")
            }
        }
    }

    fun stopGame() {
        loopJob?.cancel()
        loopJob = null
        _running.value = false;
        Log.d("Logger", "Time taken: " + (System.currentTimeMillis() - _startTime) + " ms");
        _startTime = 0L;
    }
}