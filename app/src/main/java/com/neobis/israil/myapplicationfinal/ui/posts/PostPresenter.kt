package com.neobis.israil.myapplicationfinal.ui.posts

import android.util.Log
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.StartApplication
import com.neobis.israil.myapplicationfinal.helpers.Connection
import com.neobis.israil.myapplicationfinal.model.Comment
import com.neobis.israil.myapplicationfinal.model.Post
import com.neobis.israil.myapplicationfinal.model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class PostPresenter(val view: PostContract.View?) : PostContract.Presenter {
    override fun loadInformation() {
        if (!Connection.isNetworkOnline()) {
            Log.d("Network","Error")
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        Log.d("OK","Connection")
        view?.showProgress()
        StartApplication.service.loadPosts().enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(call: Call<MutableList<Post>>?, response: Response<MutableList<Post>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    val list = response.body() ?: ArrayList()
                    view?.onSuccess(list)
                    Log.d("Success",list.size.toString())
                } else {
                    Log.d("Error","Zapros ne proshel")
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<MutableList<Post>>?, t: Throwable?) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                view?.hideProgress()
            }
        })
    }

    override fun loadComments(list: MutableList<Any>?) {
        if (!Connection.isNetworkOnline()) {
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        view?.showProgress()
        StartApplication.service.loadComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>?, response: Response<List<Comment>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    selectPostComments(list, response.body()!!)
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

    private fun selectPostComments(list: MutableList<Any>?, listOfComments: List<Comment>) {
        if(list == null)
            return
        val commentlist = ArrayList<Comment>()
        for (comment in listOfComments) {
            for (post in list) {
                if (post is WeatherInfo)
                    continue
                else {
                    post as Post
                    if (post.id == comment.postId)
                        commentlist.add(comment)
                }
            }
        }
        view?.onSelectComments(commentlist)
    }

    override fun getWeatherForCity(cityName: Int) {
        StartApplication.weatherService.getWeatherForCity( cityName,"be8e7214086300416c8309feaf053c24")
                .enqueue(object : Callback<WeatherInfo> {
                    override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                        if (response!!.isSuccessful || response.body() != null) {
                            view?.onWeatherResponse(response.body()!!)
                            Log.d("Success",response.body().toString())

                        } else {
                            Log.d("Error","Zapros ne proshel!!")

                        }
                    }

                    override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                        Log.d("Error,Failure","Zapros ne proshel")
                    }

                })
    }
}