package com.neobis.israil.myapplicationfinal.ui.posts

import com.neobis.israil.myapplicationfinal.helpers.ProgBarInterface
import com.neobis.israil.myapplicationfinal.helpers.SuccessOrError
import com.neobis.israil.myapplicationfinal.model.Comment
import com.neobis.israil.myapplicationfinal.model.Post
import com.neobis.israil.myapplicationfinal.model.WeatherInfo

interface PostContract {

    interface View : ProgBarInterface, SuccessOrError<MutableList<Post>>{
        fun onWeatherResponse(result: WeatherInfo)
        fun onSelectComments(commentlist: ArrayList<Comment>)
    }

    interface Presenter {
        fun loadInformation()
        fun loadComments(list: MutableList<Any>?)
        fun getWeatherForCity(cityId: Int)
    }

}