package se.springer.geotrain.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow
import se.springer.geotrain.components.GeoTrainLogic
import se.springer.geotrain.components.WorldPart

class SettingsScreenVM (
    application: Application
) : AndroidViewModel(application) {

    val mode: StateFlow<WorldPart> = GeoTrainLogic.worldPart

    fun setWorldPart(mode: Int) {
        GeoTrainLogic.setWorldPart(mode);
    }

    fun setGameMode(mode: Int) {
        GeoTrainLogic.setGameMode(mode)
    }
}