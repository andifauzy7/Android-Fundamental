package com.example.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Hero>()
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var itemView = p1
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, p2, false)
        }

        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(p0) as Hero
        viewHolder.bind(hero)
        return itemView
    }

    override fun getItem(p0: Int): Any {
        return heroes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return heroes.size
    }

    private inner class ViewHolder internal constructor(private val view: View) {
        fun bind(hero: Hero) {
            with(view){
                txt_name.text = hero.name
                txt_description.text = hero.description
                img_photo.setImageResource(hero.photo)
            }
        }
    }
}