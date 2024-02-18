package com.bignerdranch.android.criminalintent

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListPoliceCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(
    private val binding: ListPoliceCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(crime: Crime) {
        binding.policeCrimeTitle.text = "Serious: ${crime.title}"
        binding.policeCrimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Serious ${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.contactPoliceButton.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Serious ${crime.title} button clicked!",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("PoliceCrimeHolder", "Contact police button clicked for crime: ${crime.title}")
        }

    }
}

class CrimeListAdapter(

    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val crimeView = 0
    private val seriousCrimeView = 1
    private val TAG = "MOM"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater,parent,false)
        val policeBinding = ListPoliceCrimeBinding.inflate(inflater, parent, false)
        return when (viewType) {
            crimeView -> CrimeHolder(binding)
            seriousCrimeView -> SeriousCrimeHolder(policeBinding) // Use different holder for serious crimes
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val crime = crimes[position]

        when (holder.itemViewType) {
            crimeView -> (holder as CrimeHolder).bind(crime)
            seriousCrimeView -> (holder as SeriousCrimeHolder).bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]

        return if(crime.isSerious){
            seriousCrimeView
        }
        else{
            crimeView
        }
    }
}
