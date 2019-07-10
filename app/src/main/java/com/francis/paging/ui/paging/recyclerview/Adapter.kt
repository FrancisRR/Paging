package com.francis.paging.ui.paging.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.francis.paging.R
import com.francis.paging.db.UserModel

class Adapter(val context: Context) : PagedListAdapter<UserModel, Adapter.AdapterViewHolder>(DIFFUTILS) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val model: UserModel? = getItem(position)
        holder.tvName.text = "${model?.name}"
        holder.tvAge.text = "${model?.age}"

    }

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tvName)
        lateinit var tvName: TextView
        @BindView(R.id.tvAge)
        lateinit var tvAge: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

    }


    companion object {
        val DIFFUTILS: DiffUtil.ItemCallback<UserModel>
            get() = object : DiffUtil.ItemCallback<UserModel>() {
                override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem.id != newItem.id
                }

                override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return !oldItem.name.equals(newItem.name)
                }
            }

    }
}