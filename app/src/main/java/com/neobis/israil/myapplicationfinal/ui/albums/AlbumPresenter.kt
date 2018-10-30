package com.neobis.israil.myapplicationfinal.ui.albums

import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.StartApplication
import com.neobis.israil.myapplicationfinal.helpers.Connection
import com.neobis.israil.myapplicationfinal.model.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumPresenter(var view: AlbumContract.View?) : AlbumContract.Presenter {

    override fun loadAlbums() {
        if (!Connection.isNetworkOnline()) {
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        view?.showProgress()
        StartApplication.service.loadAlbums().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    val list = response.body() ?: ArrayList()
                    view?.onSuccess(list)
                } else {
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                view?.hideProgress()
            }
        })
    }
}