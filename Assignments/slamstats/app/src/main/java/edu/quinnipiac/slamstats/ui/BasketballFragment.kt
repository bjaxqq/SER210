package edu.quinnipiac.slamstats.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.slamstats.api.models.RankingResponse
import edu.quinnipiac.slamstats.databinding.FragmentBasketballBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import edu.quinnipiac.slamstats.R
import edu.quinnipiac.slamstats.api.models.RankingData
import androidx.navigation.fragment.findNavController

class BasketballFragment : Fragment() {
    private var _binding: FragmentBasketballBinding? = null
    private val binding get() = _binding!!
    private val args: BasketballFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        val leagueId = args.leagueId
        val season = args.season

        fetchStandings(leagueId, season)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Check out the basketball standings!")
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
                true
            }
            R.id.action_settings -> {
                val colors = arrayOf("Red", "Blue", "Green", "Yellow", "Purple")
                AlertDialog.Builder(requireContext())
                    .setTitle("Choose a color")
                    .setItems(colors) { dialog, which ->
                        // Apply the selected color to the background of the fragment
                        val colorId = when (colors[which]) {
                            "Red" -> R.color.red
                            "Blue" -> R.color.light_blue
                            "Green" -> R.color.green
                            "Yellow" -> R.color.yellow
                            "White" -> R.color.white
                            else -> R.color.white
                        }
                        binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), colorId))
                    }
                    .show()
                true
            }
            R.id.action_help -> {
                AlertDialog.Builder(requireContext())
                    .setTitle("About the App")
                    .setMessage("This app provides the latest NBA standings.\nPowered by the API-SPORTS basketball API.\nCreated by Cole Davignon and Brooks Jackson.\nCopyright 2024.")
                    .setPositiveButton("OK", null)
                    .show()
                true
            }
            R.id.action_home -> {
                findNavController().navigate(R.id.action_basketballFragment_to_mainFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchStandings(leagueId: String, season: String) {
        RetrofitClient.apiService.getRanking(leagueId, season).enqueue(object : Callback<RankingResponse> {
            override fun onResponse(call: Call<RankingResponse>, response: Response<RankingResponse>) {
                if (response.isSuccessful) {
                    val standings: List<RankingData> = response.body()?.response?.flatten() ?: emptyList()
                    val dataListWithHeaders = createListWithHeaders(standings)
                    binding.recyclerView.adapter = BasketballAdapter(dataListWithHeaders)
                } else {
                    Log.e("BasketballFragment", "Failed to fetch standings. Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RankingResponse>, t: Throwable) {
                Log.e("BasketballFragment", "Failed to fetch standings", t)
            }
        })
    }

    private fun createListWithHeaders(standings: List<RankingData>): List<Any> {
        val groupedStandings = standings.groupBy { it.group.name }.toSortedMap()
        val dataListWithHeaders = mutableListOf<Any>()
        groupedStandings.forEach { (groupName: String, teams: List<RankingData>) ->
            dataListWithHeaders.add(groupName)
            dataListWithHeaders.addAll(teams)
        }
        return dataListWithHeaders
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}