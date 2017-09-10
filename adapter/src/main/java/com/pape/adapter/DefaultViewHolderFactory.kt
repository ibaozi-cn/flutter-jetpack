package com.pape.adapter

import android.view.View

import java.lang.reflect.Constructor

/**
 * @param <ViewHolder>
</ViewHolder> */
class DefaultViewHolderFactory<ViewHolder : ItemViewHolder> {

    fun create(v: View, clazz: Class<out ViewHolder>): ViewHolder {
        try {
            try {
                val constructor = clazz.getDeclaredConstructor(View::class.java)
                //could be that the constructor is not public
                constructor.isAccessible = true
                return constructor.newInstance(v)
            } catch (e: NoSuchMethodException) {
                //maybe that viewholder has a default view
                return clazz.newInstance()
            }

        } catch (e: Exception) {
            // I am really out of options, you could have just set
            throw RuntimeException("You have to provide a ViewHolder with a constructor which takes a view!")
        }

    }
}
