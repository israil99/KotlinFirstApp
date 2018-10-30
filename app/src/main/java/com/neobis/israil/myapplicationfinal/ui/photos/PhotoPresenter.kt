package com.neobis.israil.myapplicationfinal.ui.photos

import android.util.Log
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.StartApplication
import com.neobis.israil.myapplicationfinal.helpers.Connection
import com.neobis.israil.myapplicationfinal.model.Photos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotoPresenter(var view: PhotoContract.View?): PhotoContract.Presenter {

    override fun loadPhotosOfAlbum(id: Int?) {
        if(id == null) {
            Log.d("id is not found","Error")
            return
        }
        if (!Connection.isNetworkOnline()) {
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        view?.showProgress()
        StartApplication.service.loadAlbumPhotos(id).enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>?, response: Response<List<Photos>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    view?.onSuccess(response.body() ?: ArrayList())
                } else {
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<List<Photos>>?, t: Throwable?) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                view?.hideProgress()
            }
        })
    }
}