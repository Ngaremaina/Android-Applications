package com.example.aniamcalculatorapplication.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aniamcalculatorapplication.databinding.FragmentAnglesBinding
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


class AnglesFragment : Fragment() {
    private lateinit var binding:FragmentAnglesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentAnglesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding.button4.setOnClickListener {
            val angle: String = binding.txtSine.text.toString()
            val sine: Double = angle.toDouble()
            val answer: Double = sin(sine)
            binding.textView5.text = answer.toString()
        }

        binding.button5.setOnClickListener {
            val angle: String = binding.txtCosine.text.toString()
            val cosine: Double = angle.toDouble()
            val answer: Double = cos(cosine)
            binding.textView4.text = answer.toString()
        }

        binding.button8.setOnClickListener {
            val angle: String = binding.txtTangent.text.toString()
            val tangent: Double = angle.toDouble()
            val answer: Double = tan(tangent)
            binding.textView7.text = answer.toString()
        }

        binding.button6.setOnClickListener {
            binding.txtSine.setText("")
        }

        binding.button7.setOnClickListener {
            binding.txtCosine.setText("")
        }

        binding.button9.setOnClickListener {
            binding.txtTangent.setText("")
        }
    }


}