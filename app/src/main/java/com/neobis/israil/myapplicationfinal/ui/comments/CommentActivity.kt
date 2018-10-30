package com.neobis.israil.myapplicationfinal.ui.comments

import android.os.Bundle
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.model.Comment
import com.neobis.israil.myapplicationfinal.model.Post
import com.neobis.israil.myapplicationfinal.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_comments.*


class CommentActivity : BaseActivity(), CommentContract.View {
    private var presenter: CommentContract.Presenter? = null
    private var adapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        adapter = CommentAdapter(ArrayList())
        recyclerView.adapter = adapter
        presenter = CommentPresenter(this)
        val post = intent?.getParcelableExtra<Post>("post")
        presenter?.loadComments(post?.id)

        postTitle.text = post?.title
    }

    override fun onSuccess(result: List<Comment>) {
        adapter?.setPostCommentList(result)
    }
}