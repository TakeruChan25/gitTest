package com.example.ssbbmm5.ui.detail.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssbbmm5.R
import com.example.ssbbmm5.model.User
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_tab.view.*

class FollowersAdapter: RecyclerView.Adapter<FollowersAdapter.MyHolder>() {

//    private val data : List<User>()
    private val data= ArrayList<User>()


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            with(itemView){
                Glide.with(itemView.context!!)
                    .load(user.avatarUrl)
                    .into(img_item_tab)

                tv_item_username.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = MyHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item, viewGroup, false
        )
    )

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
//        val v = holder.itemView
//        v.setOnClickListener {
//            onClickListener(data[position])
//        }
        holder.bind(data[position])
    }
}