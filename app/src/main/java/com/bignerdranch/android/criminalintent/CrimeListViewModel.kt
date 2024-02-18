package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.Random
import java.util.UUID

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()
    val seriousCrimes = mutableListOf<Crime>()
    val random = Random() // Import java.util.Random for this

    init {
        for (i in 0 until 100) {
            val isSerious = random.nextDouble() < 0.1 // Adjust the probability as needed
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                isSerious = isSerious,
                requiresPolice = isSerious
            )

            crimes += crime

        }
    }
}
