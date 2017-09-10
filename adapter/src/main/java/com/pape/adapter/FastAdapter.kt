package com.pape.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @project: KotlinMvpDemo
 * @description: //快速构建列表的适配器
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/6/27 14:55
 * @updateUser zzy05
 * @update 2017/6/27 14:55
 * @version V1.0
 * FC:9A:3B:89:7F:2D:B8:36:54:93:C6:07:4D:65:74:81:C8:11:9C:E3
 */
 class FastAdapter<T>(val layoutResourceId: Int, var items: List<T>, val init: (View, T) -> Unit) :
        RecyclerView.Adapter<FastAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId, parent, false)
        return ViewHolder(view, init)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder<in T>(view: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindData(item: T) {
            with(item) {
                init(itemView, item)
            }
        }
    }
}