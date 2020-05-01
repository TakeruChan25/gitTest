package com.example.ssbbmm5.ui.fav

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssbbmm5.R
import com.example.ssbbmm5.data.FavoriteUserHelper
import com.example.ssbbmm5.data.mapping.MappingHelper
import com.example.ssbbmm5.model.User
import com.example.ssbbmm5.ui.detail.DetailActivity
import com.example.ssbbmm5.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_fav.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavFragment : Fragment() {

    private lateinit var favoriteUserHelper: FavoriteUserHelper
    lateinit var favoriteUser: ArrayList<User>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteUserHelper = FavoriteUserHelper.getInstance(context!!)
        favoriteUserHelper.open()

        favoriteUser = favoriteUserHelper.getUSer()
        rv_fav.apply {
            adapter = FavAdapter(
                context,
                favoriteUser) {
                val i = Intent(context, DetailActivity::class.java)
                i.putExtra(HomeFragment.EXTRA, it)
                startActivity(i)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteUser.clear()
        favoriteUser.addAll(favoriteUserHelper.getUSer())
        favoriteUser.reverse()
        rv_fav.adapter?.notifyDataSetChanged()
    }
}
