package se.springer.geotrain.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GeoTrainLogic {
    private var _startTime: Long = 0L;
    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>> get() = _countryList
    private val _currentCountry = MutableStateFlow(Country("", "", countries[0].flagRes))
    val currentCountry: StateFlow<Country> get() = _currentCountry

    private val _maxCount = MutableStateFlow<Int>(0);
    val maxCount: StateFlow<Int> get() = _maxCount;


    private val _index = MutableStateFlow<Int>(0);
    val index: StateFlow<Int> get() = _index;

    private val _score = MutableStateFlow<Int>(0);
    val score: StateFlow<Int> get() = _score;

    private val _time = MutableStateFlow<Float>(0f);
    val time: StateFlow<Float> get() = _time;

    fun startGame(startTime: Long, count: Int) {
        _startTime = startTime
        _index.value = 0
        _maxCount.value = count
        _score.value = 0

        _countryList.value = getRandomCountries(_maxCount.value)
        _currentCountry.value = _countryList.value[_index.value]
    }

    private fun getRandomCountries(count: Int): List<Country> {
        return countries.shuffled().take(count)
    }

    fun guess(guess: String) {
        if (guess == _currentCountry.value.name.lowercase()) {
            _score.value++
        }
        getNext()
    }

    fun getNext() {
        _index.value++
        if (_index.value >= _countryList.value.size) return
        _currentCountry.value = _countryList.value[_index.value]
    }

    fun endGame() {
        _time.value = (System.currentTimeMillis() - _startTime) / 1000f
    }
}