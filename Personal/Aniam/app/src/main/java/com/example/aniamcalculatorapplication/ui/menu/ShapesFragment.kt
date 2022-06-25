package com.example.aniamcalculatorapplication.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniamcalculatorapplication.R
import com.example.aniamcalculatorapplication.databinding.FragmentShapesBinding

class ShapesFragment : Fragment() {
    private lateinit var binding:FragmentShapesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentShapesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button25.setOnClickListener{
            val squareInput:String=binding.txtSquare.text.toString()
            val square:Double=squareInput.toDouble()
            val area:Double=square*square
            val perimeter:Double=square*4

            binding.textView26.text=area.toString()
            binding.textView1.text=perimeter.toString()

        }

        binding.button26.setOnClickListener{
            binding.txtSquare.setText("")
        }

        binding.button28.setOnClickListener{
            val lengthInput:String=binding.txtLength.text.toString()
            val widthInput:String=binding.txtWidth.text.toString()
            val length:Double=lengthInput.toDouble()
            val width:Double=widthInput.toDouble()

            val area:Double=length*width
            val perimeter:Double=2*(length+width)

            binding.textView28.text=area.toString()
            binding.textView2.text=perimeter.toString()
        }

        binding.button27.setOnClickListener{
            binding.txtLength.setText("")
            binding.txtWidth.setText("")
        }

        binding.button29.setOnClickListener{
            val radiusInput:String=binding.txtCircle.text.toString()
            val radius:Double=radiusInput.toDouble()

            val area:Double=Math.PI*radius*radius
            val circumference:Double=2*Math.PI*radius

            binding.textView3.text=circumference.toString()
            binding.textView12.text=area.toString()
        }

        binding.button30.setOnClickListener{
            binding.txtCircle.setText("")
        }
    }



}