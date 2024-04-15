package com.example.testproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.ItemCarBinding
import java.util.ArrayList

class CarsAdapter(
    private val carList: ArrayList<Car>,
    private val listener : CarsItemListener) :
    RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarsViewHolder(
            ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val currentItem = carList[position]
        holder.binding.apply {
            imageViewItemCar.setImageResource(currentItem.titleImage)
            textViewItemTitle.text = currentItem.titleName
            textViewItemYear.text = currentItem.titleYear
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(carList[position])
        }
        println("Now it's ${currentItem.titleName}")
    }

    inner class CarsViewHolder(val binding : ItemCarBinding) : RecyclerView.ViewHolder(binding.root)

    interface CarsItemListener {
        fun onItemClick(car : Car)
    }

}