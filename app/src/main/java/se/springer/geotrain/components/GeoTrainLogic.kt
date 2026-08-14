package se.springer.geotrain.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GeoTrainLogic {
    private var _startTime: Long = 0L;
    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>> get() = _countryList
    private val _currentCountry = MutableStateFlow(Country("", WorldPart.ALL, "", countries[0].flagRes))
    val currentCountry: StateFlow<Country> get() = _currentCountry

    private val _maxCount = MutableStateFlow<Int>(0);
    val maxCount: StateFlow<Int> get() = _maxCount;

    private val _index = MutableStateFlow<Int>(0);
    val index: StateFlow<Int> get() = _index;

    private val _score = MutableStateFlow<Int>(0);
    val score: StateFlow<Int> get() = _score;

    private val _time = MutableStateFlow<Float>(0f);
    val time: StateFlow<Float> get() = _time;

    private val _lessonMode = MutableStateFlow<WorldPart>(WorldPart.ALL);
    val lessonMode: StateFlow<WorldPart> get() = _lessonMode;

    fun setMode(mode: Int) {
        _lessonMode.value = WorldPart.values()[mode];
    }

    fun startGame(startTime: Long) {
        _startTime = startTime
        _index.value = 0
        _score.value = 0

        _countryList.value = getRandomCountries()
        _maxCount.value = _countryList.value.size
        _currentCountry.value = _countryList.value[_index.value]
    }

    // get a random set of countries from some part of the world that is of user-defined size
//    private fun getRandomCountries(count: Int): List<Country> {
//        val filtered: List<Country>
//
//        if (_lessonMode.value != WorldPart.ALL) {
//            filtered = countries.filter { it.part == _lessonMode.value }
//        } else {
//            filtered = countries
//        }
//
//        require(count <= filtered.size) {
//            "Required size of countries needs to be equal or less than list of available countries."
//        }
//
//        return filtered.shuffled().take(count)
//    }

    // This function returns all countries from the given category and does not relly on wished size
    private fun getRandomCountries(): List<Country> {

        val filtered: List<Country> = if (_lessonMode.value != WorldPart.ALL) {
            countries.filter { it.part == _lessonMode.value }
        } else {
            countries
        }

        return filtered.shuffled()
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