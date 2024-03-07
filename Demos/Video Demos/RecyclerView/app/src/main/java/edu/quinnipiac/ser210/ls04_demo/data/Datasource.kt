package edu.quinnipiac.ser210.ls04_demo.data

import edu.quinnipiac.ser210.ls04_demo.R
import edu.quinnipiac.ser210.ls04_demo.model.Affirmation


class Datasource {

    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1,R.drawable.img),
            Affirmation(R.string.affirmation2,R.drawable.img_1),
            Affirmation(R.string.affirmation3,R.drawable.img_2),
            Affirmation(R.string.affirmation4,R.drawable.img_3),
            Affirmation(R.string.affirmation5,R.drawable.img),
            Affirmation(R.string.affirmation6,R.drawable.img_1),
            Affirmation(R.string.affirmation7,R.drawable.img_2),
            Affirmation(R.string.affirmation8,R.drawable.img_3),
            Affirmation(R.string.affirmation9,R.drawable.img),
            Affirmation(R.string.affirmation10,R.drawable.img_1)
        )
    }
}