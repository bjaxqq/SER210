package edu.quinnipiac.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var rulesButton: Button
    private lateinit var playerName: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        startButton = view.findViewById(R.id.startButton)
        rulesButton = view.findViewById(R.id.rulesButton)
        playerName = view.findViewById(R.id.playerName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedViewModel: SharedViewModel by activityViewModels()

        startButton.setOnClickListener {
            sharedViewModel.playerName.value = playerName.text.toString()
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }

        rulesButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_rulesFragment)
        }
    }
}
