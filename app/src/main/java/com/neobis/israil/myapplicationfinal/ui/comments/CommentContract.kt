package com.neobis.israil.myapplicationfinal.ui.comments

import com.neobis.israil.myapplicationfinal.helpers.ProgBarInterface
import com.neobis.israil.myapplicationfinal.helpers.SuccessOrError
import com.neobis.israil.myapplicationfinal.model.Comment

interface CommentContract{
    interface View : ProgBarInterface, SuccessOrError<List<Comment>>

    interface Presenter {
        fun loadComments(postId: Int?)
    }
}