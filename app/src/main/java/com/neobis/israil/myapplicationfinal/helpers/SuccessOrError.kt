package com.neobis.israil.myapplicationfinal.helpers

interface SuccessOrError<T>{
    fun onSuccess(result: T)
    fun onError(message: String?)
}