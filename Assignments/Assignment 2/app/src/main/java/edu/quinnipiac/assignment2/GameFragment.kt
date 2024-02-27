package edu.quinnipiac.assignment2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {
    private lateinit var resetButton: Button
    private lateinit var homeButton: Button
    private lateinit var title: TextView
    private val userGrids = mutableSetOf<Int>()
    private val cpuGrids = mutableSetOf<Int>()
    private val selectedGrids = mutableSetOf<Int>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var gameStatus = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        resetButton = view.findViewById(R.id.resetButton)
        homeButton = view.findViewById(R.id.homeButton)
        title = view.findViewById(R.id.title)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetButton.setOnClickListener {
            userGrids.clear()
            cpuGrids.clear()
            selectedGrids.clear()
            gameStatus = true
            title.text = "It's ${sharedViewModel.playerName.value}'s turn."
            clickableButtons(view, true)
            val gridIds = listOf(
                R.id.grid0, R.id.grid1, R.id.grid2, R.id.grid3, R.id.grid4, R.id.grid5,
                R.id.grid6, R.id.grid7, R.id.grid8, R.id.grid9, R.id.grid10, R.id.grid11,
                R.id.grid12, R.id.grid13, R.id.grid14, R.id.grid15, R.id.grid16, R.id.grid17,
                R.id.grid18, R.id.grid19, R.id.grid20, R.id.grid21, R.id.grid22, R.id.grid23,
                R.id.grid24, R.id.grid25, R.id.grid26, R.id.grid27, R.id.grid28, R.id.grid29,
                R.id.grid30, R.id.grid31, R.id.grid32, R.id.grid33, R.id.grid34, R.id.grid35
            )
            gridIds.forEach { gridId ->
                view.findViewById<ImageButton>(gridId)?.setBackgroundResource(R.drawable.tile_bg)
            }
        }

        homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_startFragment)
        }

        sharedViewModel.playerName.observe(viewLifecycleOwner) { playerName ->
            title.text = "It's $playerName's turn."
        }

        val gridIds = listOf(
            R.id.grid0, R.id.grid1, R.id.grid2, R.id.grid3, R.id.grid4, R.id.grid5,
            R.id.grid6, R.id.grid7, R.id.grid8, R.id.grid9, R.id.grid10, R.id.grid11,
            R.id.grid12, R.id.grid13, R.id.grid14, R.id.grid15, R.id.grid16, R.id.grid17,
            R.id.grid18, R.id.grid19, R.id.grid20, R.id.grid21, R.id.grid22, R.id.grid23,
            R.id.grid24, R.id.grid25, R.id.grid26, R.id.grid27, R.id.grid28, R.id.grid29,
            R.id.grid30, R.id.grid31, R.id.grid32, R.id.grid33, R.id.grid34, R.id.grid35
        )

        gridIds.forEach { gridId ->
            view.findViewById<ImageButton>(gridId)?.setOnClickListener { buttonView ->
                if (gameStatus && gridId !in userGrids && gridId !in cpuGrids) {
                    buttonView.setBackgroundResource(R.drawable.ic_red_piece)
                    userGrids.add(gridId)

                    if (winConditions(userGrids)) {
                        gameStatus = false
                        clickableButtons(view, false)
                    } else {
                        clickableButtons(view, false)
                        cpuMove(gridIds, view) {
                            clickableButtons(view, gameStatus)
                        }
                    }
                }
            }
        }
    }

    private fun cpuMove(buttonsIds: List<Int>, view: View, onMoveCompleted: () -> Unit) {
        if (!gameStatus) return

        title.text = "It's the CPU's turn."

        Handler(Looper.getMainLooper()).postDelayed({
            val gridsAvailable = buttonsIds.filterNot { it in selectedGrids }
            if (gridsAvailable.isNotEmpty()) {
                val selectedGrid = findBestMoveForCPU(gridsAvailable)
                activity?.runOnUiThread {
                    view.findViewById<ImageButton>(selectedGrid)?.let { button ->
                        button.setBackgroundResource(R.drawable.ic_yellow_piece)
                        cpuGrids.add(selectedGrid)
                        selectedGrids.add(selectedGrid)
                    }

                    if (winConditions(cpuGrids)) {
                        gameStatus = false
                        clickableButtons(view, false)
                    } else {
                        title.text = "It's ${sharedViewModel.playerName.value}'s turn."
                        onMoveCompleted()
                    }
                }
            }
        }, 3000)
    }

    private fun findBestMoveForCPU(gridsAvailable: List<Int>): Int {
        for (gridId in gridsAvailable) {
            if (gridId !in userGrids) {
                cpuGrids.add(gridId)
                selectedGrids.add(gridId)
                if (winConditions(cpuGrids)) {
                    cpuGrids.remove(gridId)
                    selectedGrids.remove(gridId)
                    return gridId
                }
                cpuGrids.remove(gridId)
                selectedGrids.remove(gridId)
            }
        }

        return gridsAvailable.random()
    }

    private fun clickableButtons(view: View, clickable: Boolean) {
        val gridIds = listOf(
            R.id.grid0, R.id.grid1, R.id.grid2, R.id.grid3, R.id.grid4, R.id.grid5,
            R.id.grid6, R.id.grid7, R.id.grid8, R.id.grid9, R.id.grid10, R.id.grid11,
            R.id.grid12, R.id.grid13, R.id.grid14, R.id.grid15, R.id.grid16, R.id.grid17,
            R.id.grid18, R.id.grid19, R.id.grid20, R.id.grid21, R.id.grid22, R.id.grid23,
            R.id.grid24, R.id.grid25, R.id.grid26, R.id.grid27, R.id.grid28, R.id.grid29,
            R.id.grid30, R.id.grid31, R.id.grid32, R.id.grid33, R.id.grid34, R.id.grid35
        )

        gridIds.forEach { gridId ->
            view.findViewById<ImageButton>(gridId)?.isEnabled = clickable
        }
    }

    private fun winConditions(selectedGrids: Set<Int>): Boolean {
        val wins = listOf (
            // Horizontal wins
            listOf(R.id.grid0, R.id.grid1, R.id.grid2, R.id.grid3),
            listOf(R.id.grid1, R.id.grid2, R.id.grid3, R.id.grid4),
            listOf(R.id.grid2, R.id.grid3, R.id.grid4, R.id.grid5),
            listOf(R.id.grid6, R.id.grid7, R.id.grid8, R.id.grid9),
            listOf(R.id.grid7, R.id.grid8, R.id.grid9, R.id.grid10),
            listOf(R.id.grid8, R.id.grid9, R.id.grid10, R.id.grid11),
            listOf(R.id.grid12, R.id.grid13, R.id.grid14, R.id.grid15),
            listOf(R.id.grid13, R.id.grid14, R.id.grid15, R.id.grid16),
            listOf(R.id.grid14, R.id.grid15, R.id.grid16, R.id.grid17),
            listOf(R.id.grid18, R.id.grid19, R.id.grid20, R.id.grid21),
            listOf(R.id.grid19, R.id.grid20, R.id.grid21, R.id.grid22),
            listOf(R.id.grid20, R.id.grid21, R.id.grid22, R.id.grid23),
            listOf(R.id.grid24, R.id.grid25, R.id.grid26, R.id.grid27),
            listOf(R.id.grid25, R.id.grid26, R.id.grid27, R.id.grid28),
            listOf(R.id.grid26, R.id.grid27, R.id.grid28, R.id.grid29),
            listOf(R.id.grid30, R.id.grid31, R.id.grid32, R.id.grid33),
            listOf(R.id.grid31, R.id.grid32, R.id.grid33, R.id.grid34),
            listOf(R.id.grid32, R.id.grid33, R.id.grid34, R.id.grid35),

            // Vertical wins
            listOf(R.id.grid0, R.id.grid6, R.id.grid12, R.id.grid18),
            listOf(R.id.grid6, R.id.grid12, R.id.grid18, R.id.grid24),
            listOf(R.id.grid12, R.id.grid18, R.id.grid24, R.id.grid30),
            listOf(R.id.grid1, R.id.grid7, R.id.grid13, R.id.grid19),
            listOf(R.id.grid7, R.id.grid13, R.id.grid19, R.id.grid25),
            listOf(R.id.grid13, R.id.grid19, R.id.grid25, R.id.grid31),
            listOf(R.id.grid2, R.id.grid8, R.id.grid14, R.id.grid20),
            listOf(R.id.grid8, R.id.grid14, R.id.grid20, R.id.grid26),
            listOf(R.id.grid14, R.id.grid20, R.id.grid26, R.id.grid32),
            listOf(R.id.grid3, R.id.grid9, R.id.grid15, R.id.grid21),
            listOf(R.id.grid9, R.id.grid15, R.id.grid21, R.id.grid27),
            listOf(R.id.grid15, R.id.grid21, R.id.grid27, R.id.grid33),
            listOf(R.id.grid4, R.id.grid10, R.id.grid16, R.id.grid22),
            listOf(R.id.grid10, R.id.grid16, R.id.grid22, R.id.grid28),
            listOf(R.id.grid16, R.id.grid22, R.id.grid28, R.id.grid34),
            listOf(R.id.grid5, R.id.grid11, R.id.grid17, R.id.grid23),
            listOf(R.id.grid11, R.id.grid17, R.id.grid23, R.id.grid29),
            listOf(R.id.grid17, R.id.grid23, R.id.grid29, R.id.grid35),

            // Diagonal wins
            listOf(R.id.grid0, R.id.grid7, R.id.grid14, R.id.grid21),
            listOf(R.id.grid1, R.id.grid8, R.id.grid15, R.id.grid22),
            listOf(R.id.grid2, R.id.grid9, R.id.grid16, R.id.grid23),
            listOf(R.id.grid6, R.id.grid13, R.id.grid20, R.id.grid27),
            listOf(R.id.grid7, R.id.grid14, R.id.grid21, R.id.grid28),
            listOf(R.id.grid8, R.id.grid15, R.id.grid22, R.id.grid29),
            listOf(R.id.grid12, R.id.grid19, R.id.grid26, R.id.grid33),
            listOf(R.id.grid13, R.id.grid20, R.id.grid27, R.id.grid34),
            listOf(R.id.grid14, R.id.grid21, R.id.grid28, R.id.grid35),
            listOf(R.id.grid3, R.id.grid8, R.id.grid13, R.id.grid18),
            listOf(R.id.grid4, R.id.grid9, R.id.grid14, R.id.grid19),
            listOf(R.id.grid5, R.id.grid10, R.id.grid15, R.id.grid20),
            listOf(R.id.grid9, R.id.grid14, R.id.grid19, R.id.grid24),
            listOf(R.id.grid10, R.id.grid15, R.id.grid20, R.id.grid25),
            listOf(R.id.grid11, R.id.grid16, R.id.grid21, R.id.grid26),
            listOf(R.id.grid15, R.id.grid20, R.id.grid25, R.id.grid30),
            listOf(R.id.grid16, R.id.grid21, R.id.grid26, R.id.grid31),
            listOf(R.id.grid17, R.id.grid22, R.id.grid27, R.id.grid32)
        )

        for (winCombination in wins) {
            var userCount = 0
            var cpuCount = 0
            for (gridId in winCombination) {
                if (gridId in userGrids) {
                    userCount++
                    if (userCount == 4) {
                        title.text = "${sharedViewModel.playerName.value} won the game!"
                        gameStatus = false
                        return true
                    }
                } else if (gridId in cpuGrids) {
                    cpuCount++
                    if (cpuCount == 4) {
                        title.text = "CPU won the game!"
                        gameStatus = false
                        return true
                    }
                }
            }
        }

        return false
    }
}
