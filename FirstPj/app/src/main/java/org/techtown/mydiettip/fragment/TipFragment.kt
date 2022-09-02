package org.techtown.mydiettip.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.techtown.mydiettip.R
import org.techtown.mydiettip.contentsList.ContentsListActivity
import org.techtown.mydiettip.databinding.FragmentHomeBinding
import org.techtown.mydiettip.databinding.FragmentTipBinding


class TipFragment : Fragment() {
    private lateinit var binding : FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tip,container,false)
        binding.category1.setOnClickListener{

            val intent = Intent(context,ContentsListActivity::class.java)
            intent.putExtra("category", "category1")
            startActivity(intent)
        }
        binding.category2.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category2")
            startActivity(intent)
        }
        binding.category3.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category3")
            startActivity(intent)
        }
        binding.category4.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category4")
            startActivity(intent)
        }
        binding.category5.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category5")
            startActivity(intent)
        }

        binding.hometab.setOnClickListener{
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)

        }
        binding.talktab.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)

        }

        return binding.root

    }


}