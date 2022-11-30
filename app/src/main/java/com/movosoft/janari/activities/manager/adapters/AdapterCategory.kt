package com.movosoft.janari.activities.manager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movosoft.janari.R
import com.movosoft.janari.models.categoryModel.Categories


class AdapterCategory(private val categoryList: List<Categories>, val context: Context) :
    RecyclerView.Adapter<AdapterCategory.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(categoryList[position])

        holder.itemView.setOnClickListener {
            //get position of selected item
            val category = categoryList[position]

//            val carId: String? = car.car_id
//
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("car_id", carId)
//            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryName)

        fun bindView(categories: Categories) {
            categoryNameTextView.text = categories.description
        }
    }
}