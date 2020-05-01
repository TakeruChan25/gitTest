package com.example.ssbbmm5.ui.detail


import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ssbbmm5.R
import com.example.ssbbmm5.data.FavoriteUserHelper
import com.example.ssbbmm5.model.User
import com.example.ssbbmm5.ui.detail.tab.FollowersFragment
import com.example.ssbbmm5.ui.detail.tab.FollowingFragment
import com.example.ssbbmm5.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var favoriteUserHelper: FavoriteUserHelper
    lateinit var user: User
    var isFavorite = false
    var isLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(FollowersFragment(), getString(R.string.followers))
        adapter.addFragment(FollowingFragment(), getString(R.string.following))
        vp_favorite.adapter = adapter
        tab_layout_favorite.setupWithViewPager(vp_favorite)
        tab_layout_favorite.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FFFFFF"))
        tab_layout_favorite.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"))
        showUser()

    }

    private fun showUser() {
        showLoading(true)
        val userDetail = intent.getParcelableExtra<User>(HomeFragment.EXTRA)
        user = userDetail
        username_login.text = userDetail!!.login
        Glide.with(this).load(user.avatarUrl + userDetail.avatarUrl).into(img_detail)
        showLoading(false)
        isLoaded = true
        isFavorite = isFavorited()
        invalidateOptionsMenu()
    }

    private fun isFavorited(): Boolean {
        favoriteUserHelper = FavoriteUserHelper(this)
        favoriteUserHelper.open()
        if (favoriteUserHelper.isUserFavorited(user.id)) {
            return true
        }
        return false
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_detail.visibility = View.VISIBLE
        } else {
            pb_detail.visibility = View.GONE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.fav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.favorite -> {
                if (isFavorite) {
                    item.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_24dp)
                    val result = favoriteUserHelper.deleteUser(user.id.toString())
                    if (result > 0) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.remove_item_from_favorite_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = isFavorited()

                    }

                } else {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_24dp)
                    val result = favoriteUserHelper.insertUser(user)

                    if (result > 0) {
                        Toast.makeText(
                            applicationContext, getString(R.string.add_item_to_favorite_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = isFavorited()

                    }
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
