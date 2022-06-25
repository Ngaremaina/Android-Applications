package com.example.aniamcalculatorapplication.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniamcalculatorapplication.databinding.FragmentPowerBinding
import kotlin.math.pow
import kotlin.math.sqrt


class PowerFragment : Fragment() {
    private lateinit var binding:FragmentPowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentPowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button11.setOnClickListener {
            val numberInput: String = binding.txtNumber.text.toString()
            val powerInput: String = binding.txtPowerForm.text.toString()

            val number: Double = numberInput.toDouble()
            val power: Double = powerInput.toDouble()

            val answer: Double = number.pow(power)
            binding.textView12.text = answer.toString()
        }

        binding.button13.setOnClickListener {
            val numberInput: String = binding.txtNum.text.toString()
            val rootInput: String = binding.txtRootForm.text.toString()

            val number: Double = numberInput.toDouble()
            val numroot: Double = rootInput.toDouble()

            val answer: Double = sqrt(number)
            binding.textView14.text = answer.toString()
        }

        binding.button12.setOnClickListener {
            binding.txtNumber.setText("")
            binding.txtPowerForm.setText("")
        }

        binding.button14.setOnClickListener {
            binding.txtNum.setText("")
            binding.txtRootForm.setText("")
        }

        binding.button15.setOnClickListener {
            val aInput: String = binding.txtFirstInput.text.toString()
            val bInput: String = binding.txtSecondInput.text.toString()
            val cInput: String = binding.txtThirdInput.text.toString()

            val a: Double = aInput.toDouble()
            val b: Double = bInput.toDouble()
            val c: Double = cInput.toDouble()

            val roots: Double = (b * b) - (4 * a * c)
            val x: Double = (-b + sqrt(b * b - 4 * a * c)) / (2 * a)
            val y: Double = (-b - sqrt(b * b - 4 * a * c)) / (2 * a)

            binding.txtRoots.text = roots.toString()
            binding.txtX.text = x.toString()
            binding.txtY.text = y.toString()

            if (roots > 0)
                binding.txtCondition.text = "Roots are real and distinct"
            else if (roots < 0)
                binding.txtCondition.text = "Roots are complex"
            else
                binding.txtCondition.text = "Roots are real and equal"

        }

        binding.button16.setOnClickListener{
            binding.txtFirstInput.setText("")
            binding.txtSecondInput.setText("")
            binding.txtThirdInput.setText("")
        }


    }
}