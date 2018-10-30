package com.neobis.israil.myapplicationfinal.ui.photos

import com.neobis.israil.myapplicationfinal.helpers.ProgBarInterface
import com.neobis.israil.myapplicationfinal.helpers.SuccessOrError
import com.neobis.israil.myapplicationfinal.model.Photos

interface PhotoContract {
    interface View : ProgBarInterface, SuccessOrError<List<Photos>>

    interface Presenter{
        fun loadPhotosOfAlbum(id: Int?)
    }
}