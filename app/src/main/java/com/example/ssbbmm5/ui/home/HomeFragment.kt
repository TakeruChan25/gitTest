package com.example.ssbbmm5.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssbbmm5.BuildConfig
import com.example.ssbbmm5.MainActivity
import com.example.ssbbmm5.R
import com.example.ssbbmm5.model.User
import com.example.ssbbmm5.model.UserResponse
import com.example.ssbbmm5.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeView {

    private lateinit var adapter: HomeAdapter
    private lateinit var presenter: HomePresenter
    private lateinit var layoutManager: LinearLayoutManager
    private var listUser: ArrayList<User> = arrayListOf()

    companion object {
        val EXTRA = "data"
        val ITEM_SAVED = "items"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEM_SAVED, listUser)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponents(savedInstanceState)

    }

    private fun initComponents(savedInstanceState: Bundle?) {
        adapter = HomeAdapter(context, listUser) {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra(EXTRA, it)
            startActivity(i)
        }
        layoutManager = LinearLayoutManager(context)
        rv_home.layoutManager = layoutManager
        rv_home.adapter = adapter
        swipe_user.setOnRefreshListener {
            doRefresh()
        }
        searchUsers()
        presenter = HomePresenter(this)
        if (savedInstanceState == null) {
            showLoading(true)
            loadData()
        } else {
            showLoading(false)
            listUser.clear()
            savedInstanceState.getParcelableArrayList<User>(ITEM_SAVED)!!.forEach { movie ->
                listUser.add(movie)
            }
        }
    }

    private fun searchUsers() {
        search_user.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    showLoading(true)
                    val searchText = search_user.text.toString().trim()
                    doSearch(searchText)
                    return true
                }
                return false
            }

        })
        search_user.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (search_user.text.trim().toString() == "") {
                    loadData()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun doRefresh() {
        if (search_user.text.trim().toString() == "") {
            presenter.setUser()
        } else {
            val s = search_user.text.trim().toString()
            loadDataSearch(s)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.main, menu)
//        val item = menu.findItem(R.id.menu_search)
//        val searchView = item.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.isEmpty()) return false
//                presenter.setUser()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//        })
//    }

    private fun doSearch(searchText: String) {
        if (searchText.isEmpty()) {
            loadData()
        } else {
            listUser.clear()
            loadDataSearch(searchText)
        }
    }

    private fun loadDataSearch(searchText: String) {
        presenter.searchUser(searchText)
    }

    private fun loadData() {
        presenter.setUser()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_home.visibility = View.VISIBLE
        } else {
            pb_home.visibility = View.GONE
        }
    }


//    atthere
    override fun onDataCompleteFromApi(data: List<User>?) {
        showLoading(false)
        swipe_user.isRefreshing = false
        listUser.clear()
        listUser.addAll(data.orEmpty())
        adapter.notifyDataSetChanged()
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.failed), Toast.LENGTH_SHORT).show()
    }
}
