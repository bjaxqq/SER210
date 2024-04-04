package edu.quinnipiac.slamstats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.slamstats.api.models.RankingData
import edu.quinnipiac.slamstats.databinding.ItemBasketballBinding

data class RankingData(val title: String)

class BasketballAdapter(private val dataList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is String -> TYPE_HEADER
            is RankingData -> TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val binding = ItemBasketballBinding.inflate(inflater, parent, false)
                TeamViewHolder(binding)
            }
        }
    }

    private var teamCount = 0
    private var lastGroupName = ""

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataList[position]) {
            is String -> {
                teamCount = 0
                lastGroupName = item
                (holder as HeaderViewHolder).bind(item)
            }
            is RankingData -> {
                if (item.group.name != lastGroupName) {
                    teamCount = 1
                    lastGroupName = item.group.name
                } else {
                    teamCount++
                }
                (holder as TeamViewHolder).bind(item, teamCount)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(android.R.id.text1)

        fun bind(header: String) {
            textView.text = header
        }
    }

    class TeamViewHolder(private val binding: ItemBasketballBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RankingData, teamCount: Int) {
            "$teamCount. ${data.team.name}".also { binding.textViewTitle.text = it }
        }
    }
}