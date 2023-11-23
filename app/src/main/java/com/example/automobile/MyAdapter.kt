package com.example.automobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.automobile.data.CarData


class MyAdapter(private val context: android.content.Context, private val dataList: List<CarData>):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].carImg).into(holder.recImg)
        holder.carTitle.text = dataList[position].carName
        holder.carDesc.text = dataList[position].carModel
        holder.carPriority.text = dataList[position].carEngine
    }

}
class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var recImg: ImageView
    var carTitle: TextView
    var carDesc: TextView
    var carPriority: TextView
    var carCard: CardView

    init {
        recImg = itemView.findViewById(R.id.carImg)
        carTitle = itemView.findViewById(R.id.recTitle)
        carDesc = itemView.findViewById(R.id.recDesc)
        carPriority = itemView.findViewById(R.id.recPriority)
        carCard = itemView.findViewById(R.id.carCard)
    }
}