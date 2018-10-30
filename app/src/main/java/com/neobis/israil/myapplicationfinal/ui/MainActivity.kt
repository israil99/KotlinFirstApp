package com.neobis.israil.myapplicationfinal.ui

import org.jetbrains.anko.toast
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.R.id.post
import com.neobis.israil.myapplicationfinal.ui.albums.AlbumFragment
import com.neobis.israil.myapplicationfinal.ui.posts.PostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
           when (item.itemId) {

                R.id.post-> {
                    if (getVisibleFragment() !is PostFragment) {
                        loadFragment(PostFragment(),"post")
                    }else
                        toast("Swipe to refresh!")
                }
               R.id.album -> {
                   if (getVisibleFragment() !is AlbumFragment) {
                       loadFragment(AlbumFragment(),"album")//set tag
                   }else
                       toast("Swipe to refresh!")

                }
            }

            false
        }
        bottomNavigationView.selectedItemId = R.id.post

    }


    private fun getVisibleFragment(): Fragment? {
        val fragmentManager = this@MainActivity.supportFragmentManager
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible)
                return fragment
        }
        return null
    }


    //switching fragment
    private fun loadFragment(fragment: android.support.v4.app.Fragment?,tag:String): Boolean {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment,tag)
                    .commit()
            return true
        }
        return false
    }
}