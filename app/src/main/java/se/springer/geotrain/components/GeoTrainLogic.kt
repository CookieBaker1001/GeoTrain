package se.springer.geotrain.components

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GeoTrainLogic {
    private var _startTime: Long = 0L;
//    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
//    val countryList: StateFlow<List<Country>> get() = _countryList
//    private val _currentCountry = MutableStateFlow(Country(listOf(""), WorldPart.ALL, "", countries[0].flagRes))
//    val currentCountry: StateFlow<Country> get() = _currentCountry
//
//    private val _landscapeList = MutableStateFlow<List<Landscape>>(emptyList())
//    val landscapeList: StateFlow<List<Landscape>> get() = _landscapeList
//    private val _currentLandscape = MutableStateFlow(Landscape("", landscapes[0].flagRes))
//    val currentLandscape: StateFlow<Landscape> get() = _currentLandscape

    private val _itemList = MutableStateFlow<List<QuizItem>>(emptyList())
    val itemList: StateFlow<List<QuizItem>> get() = _itemList
    private val _currentItem = MutableStateFlow<QuizItem?>(null)
    val currentItem: StateFlow<QuizItem?> get() = _currentItem

    private val _maxCount = MutableStateFlow(0);
    val maxCount: StateFlow<Int> get() = _maxCount;

    private val _index = MutableStateFlow(0);
    val index: StateFlow<Int> get() = _index;

    private val _score = MutableStateFlow(0);
    val score: StateFlow<Int> get() = _score;

    private val _time = MutableStateFlow(0f);
    val time: StateFlow<Float> get() = _time;

    private val _worldPart = MutableStateFlow(WorldPart.ALL);
    val worldPart: StateFlow<WorldPart> get() = _worldPart;

    private val _gameMode = MutableStateFlow(0);
    val gameMode: StateFlow<Int> get() = _gameMode;

    fun setGameMode(mode: Int) {
        _gameMode.value = mode
    }

    fun setWorldPart(mode: Int) {
        _worldPart.value = WorldPart.entries.toTypedArray()[mode];
    }

    fun startGame(startTime: Long) {
        _startTime = startTime
        _index.value = 0
        _score.value = 0

        if (_gameMode.value == 0) _itemList.value = getRandomCountries()
        else _itemList.value = landscapes.shuffled()

        _maxCount.value = _itemList.value.size
        _currentItem.value = _itemList.value[_index.value]
    }

    // This function returns all countries from the given category and does not relly on wished size
    private fun getRandomCountries(): List<Country> {
        val filtered: List<Country> = if (_worldPart.value != WorldPart.ALL) {
            countries.filter { it.part == _worldPart.value }
        } else {
            countries
        }
        return filtered.shuffled()
    }

    fun guess(guess: String) {
        for (name : String in _currentItem.value?.names!!) {
            if (guess.equals(name, ignoreCase = true)) {
                _score.value++
                break
            }
        }
        if (_index.value < _maxCount.value) getNext()

        //        if (_gameMode.value == 0) {
//            for (name in _currentCountry.value.name) {
//                if (guess.equals(name, ignoreCase = true)) {
//                    _score.value++
//                    break
//                }
//            }
//        }
//        else {
//            if (guess.equals(_currentLandscape.value.name, ignoreCase = true)) {
//                _score.value++
//            }
//        }
    }

    fun getNext() {
        _index.value++

        if (_index.value >= _itemList.value.size) return
        _currentItem.value = _itemList.value[_index.value]

//        if (_gameMode.value == 0) {
//            if (_index.value >= _countryList.value.size) return
//            _currentCountry.value = _countryList.value[_index.value]
//        } else {
//            if (_index.value >= _landscapeList.value.size) return
//            _currentLandscape.value = _landscapeList.value[_index.value]
//        }
    }

    fun endGame() {
        _time.value = (System.currentTimeMillis() - _startTime) / 1000f
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
}