package ir.ah.weather.ui.currentweather.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ir.ah.weather.base.BaseAdapter
import ir.ah.weather.data.model.DayWeather
import ir.ah.weather.databinding.ItemCurrentForecastBinding
import ir.ah.weather.di.EndPoint
import ir.ah.weather.di.EndPoint.API_IMAGE_URL
import javax.inject.Inject

class CurrentForecastAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<DayWeather, CurrentForecastAdapter.CurrentForecastViewHolder>() {

    inner class CurrentForecastViewHolder(private val binding: ItemCurrentForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

         fun bind(dayWeather: DayWeather) {
            binding.apply {
                glide.load("${API_IMAGE_URL +dayWeather.weather.get(0).icon}.png").into(binding.itemCurrentForecastImageView)

                binding.itemCurrentForecastTemp.text=dayWeather.main.temp.toString()+"°"
                binding.itemCurrentForecastTime.text=dayWeather.getHourOfDay()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentForecastViewHolder {
        val binding = ItemCurrentForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentForecastViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(currentItem)
            }
        }

    }


}