package com.example.aniamcalculatorapplication.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniamcalculatorapplication.R
import com.example.aniamcalculatorapplication.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    lateinit var binding:FragmentStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button19.setOnClickListener{
            val lcbInput:String=binding.txtLCBMode.text.toString()
            val fInput:String=binding.txtFrequencyMode.text.toString()
            val faInput:String=binding.txtFrequencyAfterMode.text.toString()
            val fbInput:String=binding.txtFrequencyBeforeMode.text.toString()
            val iInput:String=binding.txtClassIntervalMode.text.toString()

            val lcb:Double=lcbInput.toDouble()
            val f:Double=fInput.toDouble()
            val fa:Double=faInput.toDouble()
            val fb:Double=fbInput.toDouble()
            val i:Double=iInput.toDouble()

            val answer:Double=lcb+((f-fa)/(2*f-fa-fb))*i
            binding.textView20.text=answer.toString()
        }

        binding.button20.setOnClickListener{
            binding.txtLCBMode.setText("")
            binding.txtFrequencyMode.setText("")
            binding.txtFrequencyAfterMode.setText("")
            binding.txtFrequencyBeforeMode.setText("")
            binding.txtClassIntervalMode.setText("")
        }

        binding.button21.setOnClickListener{
            val lcbInput:String=binding.txtLCBMedian.text.toString()
            val cfInput:String=binding.txtCFMedian.text.toString()
            val fInput:String=binding.txtFrequencyMedian1.text.toString()
            val NInput:String=binding.txtN.text.toString()
            val xInput:String=binding.txtClassX.text.toString()

            val lcb:Double=lcbInput.toDouble()
            val cf:Double=cfInput.toDouble()
            val f:Double=fInput.toDouble()
            val N:Double=NInput.toDouble()
            val x:Double=xInput.toDouble()

            val answer:Double=lcb+(((N/2)-cf)/f)*x
            binding.textView22.text=answer.toString()
        }

        binding.button22.setOnClickListener{
            binding.txtLCBMedian.setText("")
            binding.txtFrequencyMedian1.setText("")
            binding.txtCFMedian.setText("")
            binding.txtN.setText("")
            binding.txtClassX.setText("")
        }

        binding.button23.setOnClickListener{
            val lcbInput:String=binding.txtLCBMedian1.text.toString()
            val cfinput:String=binding.txtCumulativeFrequency1.text.toString()
            val fInput:String=binding.txtFrequencyMedian.text.toString()
            val xInput:String=binding.txtClassInterval.text.toString()
            val nInput:String=binding.txtNMedian.text.toString()

            val lcb:Double=lcbInput.toDouble()
            val cf:Double=cfinput.toDouble()
            val f:Double=fInput.toDouble()
            val x:Double=xInput.toDouble()
            val n:Double=nInput.toDouble()

            val answer:Double=lcb+((((n+1)/2)-cf)/f)*x
            binding.textView24.text=answer.toString()

        }
        binding.button24.setOnClickListener{
            binding.txtLCBMedian1.setText("")
            binding.txtCumulativeFrequency1.setText("")
            binding.txtFrequencyMedian.setText("")
            binding.txtClassInterval.setText("")
            binding.txtNMedian.setText("")

        }

    }


}