package com.neobis.israil.myapplicationfinal.ui.albums

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.model.Album
import com.neobis.israil.myapplicationfinal.ui.photos.PhotoFragment
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment: Fragment(), AlbumContract.View, AlbumAdapter.Listener {
    private var presenter: AlbumContract.Presenter? = null
    private var adapter: AlbumAdapter? = null
    private var list: List<Album> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        adapter = AlbumAdapter(ArrayList(), this)
        recyclerView.adapter = adapter

        presenter = AlbumPresenter(this)
        presenter?.loadAlbums()

        swipeRefresh.setOnRefreshListener {
            presenter?.loadAlbums()
        }
    }

    override fun onSuccess(result: List<Album>) {
        this.list = result
        adapter?.setAlbumList(result)
    }



    override fun onItemAtClicked(album: Album) {
        val intent = Intent(context, PhotoFragment::class.java)
        intent.putExtra("album", album)
        startActivity(intent)
    }

    override fun onError(message: String?) {
    }

    override fun showProgress() {
        swipeRefresh?.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefresh?.isRefreshing = false
    }



}