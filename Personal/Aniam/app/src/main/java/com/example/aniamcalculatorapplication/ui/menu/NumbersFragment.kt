package com.example.aniamcalculatorapplication.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniamcalculatorapplication.R
import com.example.aniamcalculatorapplication.databinding.FragmentNumbersBinding
import kotlin.math.exp
import kotlin.math.ln


class NumbersFragment : Fragment() {
    lateinit var binding:FragmentNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentNumbersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            val factorialInput:String=binding.txtFactorial.text.toString()
            val factorial:Double=factorialInput.toDouble()
            //val answer:Double=Math
        }

        binding.button3.setOnClickListener{
            val exponentialInput:String=binding.txtExponential.text.toString()
            val exponential:Double=exponentialInput.toDouble()
            val answer:Double= exp(exponential)
            binding.textView16.text=answer.toString()
        }
        binding.button17.setOnClickListener{
            val logarithmInput:String=binding.txtLogarithm.text.toString()
            val logarithm:Double=logarithmInput.toDouble()
            val answer:Double = ln(logarithm)
            binding.textView18.text=answer.toString()

        }
        binding.button10.setOnClickListener{
            binding.txtExponential.setText("")
        }

    }


}