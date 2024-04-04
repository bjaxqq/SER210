package edu.quinnipiac.slamstats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.slamstats.R
import edu.quinnipiac.slamstats.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val generateButton: Button = view.findViewById(R.id.generateButton)
        val leagueIdEditText: EditText = view.findViewById(R.id.editTextTextMultiLine1)
        val seasonEditText: EditText = view.findViewById(R.id.editTextTextMultiLine2)

        generateButton.setOnClickListener {
            val leagueId = leagueIdEditText.text.toString()
            val season = seasonEditText.text.toString()

            if (leagueId.isNotBlank() && season.isNotBlank()) {
                val action = MainFragmentDirections.actionMainFragmentToBasketballFragment(leagueId, season)
                findNavController().navigate(action)
            } else {
                // Show error message to the user
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}