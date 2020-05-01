package com.example.ssbbmm5.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssbbmm5.R
import com.example.ssbbmm5.model.User
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item.view.tv_login

class HomeAdapter(
    private val context: Context?,
    private val data : List<User>,
    private val onClickListener: (User) -> Unit
): RecyclerView.Adapter<HomeAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            with(itemView){
                Glide.with(itemView.context!!)
                    .load(user.avatarUrl)
                    .into(img_item_photo)

                tv_login.text = user.login
                tv_html.text = user.htmlUrl
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
        val v = holder.itemView
        v.setOnClickListener {
            onClickListener(data[position])
        }
        holder.bind(data[position])
    }

}