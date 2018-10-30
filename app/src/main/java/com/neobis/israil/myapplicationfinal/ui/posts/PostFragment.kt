package com.neobis.israil.myapplicationfinal.ui.posts

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.helpers.Error
import com.neobis.israil.myapplicationfinal.helpers.Helper
import com.neobis.israil.myapplicationfinal.model.Comment
import com.neobis.israil.myapplicationfinal.model.Post
import com.neobis.israil.myapplicationfinal.model.WeatherInfo
import com.neobis.israil.myapplicationfinal.ui.comments.CommentActivity
import com.neobis.israil.myapplicationfinal.ui.comments.CommentAdapter
import kotlinx.android.synthetic.main.fragment_post.*
import java.util.*

class PostFragment : Fragment(), PostContract.View, PostAdapter.Listener {
    private var presenter: PostContract.Presenter? = null
    private var adapter: PostAdapter? = null
    private var commentsAdapter: CommentAdapter? = null
    private var list: MutableList<Any> = ArrayList()
    private var isSelectedElements = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        adapter = PostAdapter(list, this)
        recyclerView.adapter = adapter
        presenter = PostPresenter(this)

        presenter?.loadInformation()
        getWeatherFor()

        swipeRefresh.setOnRefreshListener {

            presenter?.loadInformation()
            getWeatherFor()
        }
    }

    override fun onPostClicked(post: Post) {
        val intent = Intent(context, CommentActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

    override fun onWeatherClicked() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    override fun onSuccess(result: MutableList<Post>) {
        this.list.addAll(result)
        adapter?.setPostList(this.list)
        recyclerView.adapter = adapter
    }

    override fun onError(message: String?) {
        Error.errorToLog(message)
    }

    override fun showProgress() {
        swipeRefresh?.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefresh?.isRefreshing = false
        isSelectedElements = false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.top_menu_buttons, menu)
    }

    override fun onWeatherResponse(result: WeatherInfo) {
        Log.d("Size",result.toString())
        adapter?.addAtRandom(result)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.shuffle -> {
                adapter?.setPostList(Helper.selectTenElements(list))
                isSelectedElements = true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getWeatherFor() {
        val bishkekId = 2172797 // Bishkek
        val cholponAta = "Cholpon-Ata"       // Osh
        val naryn = "Naryn"     // Нарын

        presenter?.getWeatherForCity(bishkekId)
       /* presenter?.getWeatherForCity(cholponAta)
        presenter?.getWeatherForCity(naryn)*/
    }

    override fun onSelectComments(commentlist: ArrayList<Comment>) {
        if(commentsAdapter == null)
            commentsAdapter = CommentAdapter(commentlist)
        else
            commentsAdapter?.setPostCommentList(commentlist)
        recyclerView.adapter = commentsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        commentsAdapter = null
    }
}