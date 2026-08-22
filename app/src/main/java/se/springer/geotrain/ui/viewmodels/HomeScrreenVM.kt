package se.springer.geotrain.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow
import se.springer.geotrain.components.GeoTrainLogic
import se.springer.geotrain.components.WorldPart

class HomeScreenVM (
    application: Application
) : AndroidViewModel(application) {

    val worldPart: StateFlow<WorldPart> = GeoTrainLogic.worldPart
}