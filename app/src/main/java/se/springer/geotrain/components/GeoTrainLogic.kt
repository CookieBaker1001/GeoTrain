package se.springer.geotrain.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GeoTrainLogic {

    private var _startTime: Long = 0L;

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>> get() = _countryList
    private val _currentCountry = MutableStateFlow(Country("", "", countries[0].flagRes))
    val currentCountry: StateFlow<Country> get() = _currentCountry

    private var maxCount: Int = 0;
    private var index: Int = 0;

    fun startGame(startTime: Long, count: Int) {
        _startTime = startTime
        index = 0
        maxCount = count

        _countryList.value = getRandomCountries(maxCount)
        _currentCountry.value = _countryList.value[index]
    }

    private fun getRandomCountries(count: Int): List<Country> {
        return countries.shuffled().take(count)
    }

    fun guess(guess: String) {
        if (guess == _currentCountry.value.name.lowercase()) {
            getNext()
        }
    }

    fun getNext() {
        index++
        if (index >= _countryList.value.size) return
        _currentCountry.value = _countryList.value[index]
    }
}