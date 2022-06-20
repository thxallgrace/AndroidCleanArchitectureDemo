package com.eunhye.presentation.views.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eunhye.data.utils.LAST_PAGE
import com.eunhye.domain.model.search.Movie
import com.eunhye.domain.usecase.GetLocalMoviesUseCase
import com.eunhye.domain.usecase.GetMoviesUseCase
import com.eunhye.domain.usecase.GetPagingMoviesUseCase
import com.eunhye.presentation.base.BaseViewModel
import com.eunhye.presentation.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getPagingMoviesUseCase: GetPagingMoviesUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val networkManager: NetworkManager
) : BaseViewModel() {
    private var currentQuery: String = ""
    val query = MutableLiveData<String>()
    private val _movieList = MutableLiveData<MutableList<Movie>>()
    private val _toastMsg = MutableLiveData<MessageSet>()

    val movieList: LiveData<MutableList<Movie>> get() = _movieList
    val toastMsg: LiveData<MessageSet> get() = _toastMsg

    fun requestMovie() {
        currentQuery = query.value.toString().trim()
        if (currentQuery.isEmpty()) {
            _toastMsg.value = MessageSet.EMPTY_QUERY
            return
        }

        if (!checkNetworkState()) {
            return
        }

        compositeDisposable.add(
            getMoviesUseCase.execute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    if (movies.isEmpty()) {
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                }, {
                    Log.d("AAAAAAAAA11", it.message.toString())
                    Log.d("AAAAAAAAA11", it.localizedMessage.toString())
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestPagingMovie(offset: Int) {
        if (!checkNetworkState()){
            return
        }

        compositeDisposable.add(
            getPagingMoviesUseCase.execute(currentQuery, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    val pagingMovieList = _movieList.value
                    pagingMovieList?.addAll(movies)
                    _movieList.value = pagingMovieList
                    _toastMsg.value = MessageSet.SUCCESS
                }, {
                    when (it.message) {
                        LAST_PAGE -> _toastMsg.value = MessageSet.LAST_PAGE
                        else -> {
                            _toastMsg.value = MessageSet.ERROR
                            Log.d("AAAAAAAAA22", it.message.toString())
                            Log.d("AAAAAAAAA22", it.localizedMessage.toString())
                        }

                    }
                })
        )
    }

    private fun checkNetworkState(): Boolean {
        return if (networkManager.checkNetworkState()) {
            true
        } else {
            requestLocalMovies()
            false
        }
    }

    private fun requestLocalMovies() {
        compositeDisposable.add(
            getLocalMoviesUseCase.execute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ showProgress() }
                .doAfterTerminate{ hideProgress() }
                .subscribe({ movies ->
                    if (movies.isEmpty()) {
                        _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                    } else {
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.LOCAL_SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                })
        )
    }

    enum class MessageSet {
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_NOT_CONNECTED,
        ERROR,
        SUCCESS,
        NO_RESULT,
        LOCAL_SUCCESS
    }

}