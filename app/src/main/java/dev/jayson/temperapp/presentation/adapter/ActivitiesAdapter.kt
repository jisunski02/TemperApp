package dev.jayson.temperapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jayson.temperapp.data.model.ActivityModel
import dev.jayson.temperapp.databinding.ActivitiesLayoutBinding


class ActivitiesAdapter(private var activities: List<ActivityModel>) : RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {
    // Consider using more efficient methods like DiffUtil for better performance

    class ActivityViewHolder(val binding: ActivitiesLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivitiesLayoutBinding.inflate(layoutInflater, parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.binding.apply {
            tvLevelDescription.text = activity.title
        }

        // You can also set click listeners here
    }

    override fun getItemCount(): Int = activities.size

    // Function to submit a new list of activities
    fun submitList(newList: List<ActivityModel>) {
        val diffResult = DiffUtil.calculateDiff(ActivitiesDiffCallback(activities, newList))
        activities = newList
        diffResult.dispatchUpdatesTo(this)
    }

    // ViewHolder class remains the same

    // DiffUtil Callback class
    private class ActivitiesDiffCallback(
        private val oldList: List<ActivityModel>,
        private val newList: List<ActivityModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}