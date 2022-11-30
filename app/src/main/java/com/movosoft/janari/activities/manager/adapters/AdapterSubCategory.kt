package com.movosoft.janari.activities.manager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movosoft.janari.R
import com.movosoft.janari.models.subCategoryModel.SubCategories

class AdapterSubCategory(private val subCategoryList: List<SubCategories>, val context: Context) :
    RecyclerView.Adapter<AdapterSubCategory.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.sub_category_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(subCategoryList[position])

        holder.itemView.setOnClickListener {
            //get position of selected item
            val subCategory = subCategoryList[position]

//            val carId: String? = car.car_id
//
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("car_id", carId)
//            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subCategoryNameTextView: TextView = itemView.findViewById(R.id.subCategoryName)

        fun bindView(subCategories: SubCategories) {
            subCategoryNameTextView.text = subCategories.description
        }
    }
}