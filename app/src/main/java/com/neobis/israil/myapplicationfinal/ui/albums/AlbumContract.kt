package com.neobis.israil.myapplicationfinal.ui.albums

import com.neobis.israil.myapplicationfinal.helpers.ProgBarInterface
import com.neobis.israil.myapplicationfinal.helpers.SuccessOrError
import com.neobis.israil.myapplicationfinal.model.Album

interface AlbumContract {

    interface View : ProgBarInterface, SuccessOrError<List<Album>>

    interface Presenter {
        fun loadAlbums()
    }
}