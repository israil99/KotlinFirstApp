package com.neobis.israil.myapplicationfinal.ui.comments

import android.util.Log
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.StartApplication
import com.neobis.israil.myapplicationfinal.helpers.Connection
import com.neobis.israil.myapplicationfinal.model.Comment
import com.neobis.israil.myapplicationfinal.ui.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentPresenter(var view: CommentContract.View?) : CommentContract.Presenter {

    override fun loadComments(postId: Int?) {
        if(postId == null) {
            Log.d("Post is not found","ERROR")
            return
        }
        if (!Connection.isNetworkOnline()) {
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        view?.showProgress()
        StartApplication.service.loadPostComments(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>?, response: Response<List<Comment>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    view?.onSuccess(response.body() ?: ArrayList())
                } else {
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<List<Comment>>?, t: Throwable?) {

                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                view?.hideProgress()
            }
        })
    }
}