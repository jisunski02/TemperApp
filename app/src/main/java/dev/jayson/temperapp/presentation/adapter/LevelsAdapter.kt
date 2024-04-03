package dev.jayson.temperapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jayson.temperapp.data.model.LevelModel
import dev.jayson.temperapp.databinding.LevelsLayoutBinding

class LevelsAdapter(private var levels: List<LevelModel>) : RecyclerView.Adapter<LevelsAdapter.LevelViewHolder>() {

    class LevelViewHolder(val binding: LevelsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(level: LevelModel) {
            binding.tvLevel.text = "LEVEL ${level.level}"
            binding.tvLevelTitle.text = level.title
            binding.tvLevelDescription.text = level.description
            // Initialize the RecyclerView for activities within this level
            val activitiesAdapter = level.activities?.let { ActivitiesAdapter(it) }
            binding.rvActivities.apply {
                // Set the LayoutManager to GridLayoutManager with 2 spans/columns
                layoutManager = GridLayoutManager(context, 2)
                adapter = activitiesAdapter

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val binding = LevelsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LevelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levels[position])
    }

    override fun getItemCount(): Int = levels.size

    // Method to update the adapter's data
    fun updateLevels(newLevels: List<LevelModel>) {
        levels = newLevels
        notifyDataSetChanged()
    }

}
