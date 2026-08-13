package se.springer.geotrain.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow
import se.springer.geotrain.components.GeoTrainLogic

class EndGameScreenVM (
    application: Application
) : AndroidViewModel(application) {
    val score: StateFlow<Int> = GeoTrainLogic.score
    val max: StateFlow<Int> = GeoTrainLogic.maxCount
    val time: StateFlow<Float> = GeoTrainLogic.time
}