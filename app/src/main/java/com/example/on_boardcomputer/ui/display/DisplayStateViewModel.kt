package com.example.on_boardcomputer.ui.display

import android.app.Application
import androidx.lifecycle.*
import com.example.on_boardcomputer.database.AverageStat
import com.example.on_boardcomputer.database.StatDatabaseDao
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.*
import java.util.*

class DisplayStateViewModel(
    val dataSource: StatDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private var startMeasurement = MutableLiveData<AverageStat?>()

    val startButtonVisible = Transformations.map(startMeasurement) {
        null == it
    }
    val endButtonVisible = Transformations.map(startMeasurement) {
        null != it
    }

    fun onStopTracking() {
        uiScope.launch {
            val oldMeasurement = startMeasurement.value ?: return@launch
            oldMeasurement.endMeasuringMilli = System.currentTimeMillis()
            update(oldMeasurement)

//            Log.i("check", "")
            startMeasurement.value = null
        }
    }

    private suspend fun update(stat: AverageStat) {
        withContext(Dispatchers.IO) {
            dataSource.update(stat)
        }
    }

    private fun initializeTonight() {
        uiScope.launch {
            startMeasurement.value = getTonightFromDatabase()
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            val newMeasurement = AverageStat()
            insert(newMeasurement)
            startMeasurement.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(measurement: AverageStat) {
        withContext(Dispatchers.IO) {
            dataSource.insert(measurement)
        }
    }

    private suspend fun getTonightFromDatabase(): AverageStat? {
        return withContext(Dispatchers.IO) {
            var measurement = dataSource.getCurrentMeasurement()

            if (measurement?.endMeasuringMilli != measurement?.startMeasuringMilli) {
                measurement = null
            }
            measurement
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _tOnBoard = MutableLiveData<Double>()
    val tOnBoard: LiveData<Double>
        get() = _tOnBoard
    private val _tEngine = MutableLiveData<Double>()
    val tEngine: LiveData<Double>
        get() = _tEngine
    private val _voltage = MutableLiveData<Double>()
    val voltage: LiveData<Double>
        get() = _voltage

    private val _seriesEngine = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesEngine: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesEngine
    private val _seriesVoltage = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesVoltage: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesVoltage
    private val _seriesOnBoard = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesOnBoard: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesOnBoard

    private val _seriesVoltageMin = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesVoltageMin: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesVoltageMin
    private val _seriesVoltageMax = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesVoltageMax: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesVoltageMax
    private val _seriesOnBoardMin = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesOnBoardMin: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesOnBoardMin
    private val _seriesOnBoardMax = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesOnBoardMax: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesOnBoardMax
    private val _seriesEngineMin = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesEngineMin: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesEngineMin
    private val _seriesEngineMax = MutableLiveData<LineGraphSeries<DataPoint>>()
    val seriesEngineMax: LiveData<LineGraphSeries<DataPoint>>
        get() = _seriesEngineMax

    init {
        initializeTonight()
        _seriesVoltage.value = LineGraphSeries<DataPoint>()
        _seriesOnBoard.value = LineGraphSeries<DataPoint>()
        _seriesEngine.value = LineGraphSeries<DataPoint>()
        _seriesVoltageMin.value = LineGraphSeries<DataPoint>()
        _seriesVoltageMax.value = LineGraphSeries<DataPoint>()
        _seriesOnBoardMin.value = LineGraphSeries<DataPoint>()
        _seriesOnBoardMax.value = LineGraphSeries<DataPoint>()
        _seriesEngineMin.value = LineGraphSeries<DataPoint>()
        _seriesEngineMax.value = LineGraphSeries<DataPoint>()
        _voltage.value = 0.0
        _tEngine.value = 0.0
        _tOnBoard.value = 0.0
    }

    fun voltageChange(min: Double, max: Double) {
        _voltage.value = _voltage.value?.plus(1)
        val currentDate = Date()
        _seriesVoltage.value?.appendData(
            DataPoint(
                currentDate,
                _voltage.value!!
            ),
            true,
            200
        )
        _seriesVoltageMin.value?.appendData(
            DataPoint(
                currentDate,
                min
            ),
            true,
            200
        )
        _seriesVoltageMax.value?.appendData(
            DataPoint(
                currentDate,
                max
            ),
            true,
            200
        )
    }

    fun engineChange(min: Double, max: Double) {
        _tEngine.value = _tEngine.value?.plus(1)
        val currentDate = Date()
        _seriesEngine.value?.appendData(
            DataPoint(
                currentDate,
                _tEngine.value!!
            ),
            true,
            200
        )
        _seriesEngineMin.value?.appendData(
            DataPoint(
                currentDate,
                min
            ),
            true,
            200
        )
        _seriesEngineMax.value?.appendData(
            DataPoint(
                currentDate,
                max
            ),
            true,
            200
        )
    }

    fun onBoardChange(min: Double, max: Double) {
        _tOnBoard.value = _tOnBoard.value?.plus(1)
        val currentDate = Date()
        _seriesOnBoard.value?.appendData(
            DataPoint(
                currentDate,
                _tOnBoard.value!!
            ),
            true,
            100
        )
        _seriesOnBoardMin.value?.appendData(
            DataPoint(
                currentDate,
                min
            ),
            true,
            100
        )
        _seriesOnBoardMax.value?.appendData(
            DataPoint(
                currentDate,
                max
            ),
            true,
            100
        )
    }


}