package com.example.ssbbmm5.ui.detail.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.ssbbmm5.R
import com.example.ssbbmm5.model.User
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.fragment_following.*

/**
 * A simple [Fragment] subclass.
 */
class FollowingFragment : Fragment(), TabView {

    private lateinit var adapter: FollowersAdapter
    private lateinit var presenter: FollowersPresenter
    private lateinit var layoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FollowersAdapter()
        showRecyclerView()
        loadData()
    }

    private fun loadData() {
        presenter = FollowersPresenter(this)
        presenter.setFollowing()
    }

    private fun showRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        rv_following.layoutManager = layoutManager
        rv_following.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onDataCompleteFromApi(data: List<User>?) {
//
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
//
    }

}
