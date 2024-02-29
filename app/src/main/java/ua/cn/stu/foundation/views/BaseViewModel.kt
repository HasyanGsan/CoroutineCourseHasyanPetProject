package ua.cn.stu.foundation.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.cn.stu.foundation.model.ErrorResult
import ua.cn.stu.foundation.model.PendingResult
import ua.cn.stu.foundation.model.Result
import ua.cn.stu.foundation.model.SuccessResult

import ua.cn.stu.foundation.model.dispatchers.Dispatcher
import ua.cn.stu.foundation.utils.Event
import java.lang.Exception

typealias LiveEvent<T> = LiveData<Event<T>>
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

typealias LiveResult<T> = LiveData<Result<T>>
typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias MediatorLiveResult<T> = MediatorLiveData<Result<T>>

/**
 * Base class for all view-models.
 */
open class BaseViewModel : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        clearTasks()
    }

    /**
     * Override this method in child classes if you want to listen for results
     * from other screens
     */
    open fun onResult(result: Any) {

    }

    /**
     * Override this method in child classes if you want to control go-back behaviour.
     * Return `true` if you want to abort closing this screen
     */
    open fun onBackPressed(): Boolean {
        clearTasks()
        return false
    }

    /**
     * Launch task asynchronously and map its result to the specified
     * [liveResult].
     * Task is cancelled automatically if view-model is going to be destroyed.
     */
    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T) {
        viewModelScope.launch {
            try{
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception){
                liveResult.postValue(ErrorResult(e))
            }
        }
    }

    private fun clearTasks() {

    }

}