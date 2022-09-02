package org.techtown.mydiettip.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.techtown.mydiettip.R
import org.techtown.mydiettip.board.BoardLVAdapter
import org.techtown.mydiettip.board.BoardModel
import org.techtown.mydiettip.contentsList.ContentModel
import org.techtown.mydiettip.databinding.FragmentHomeBinding
import org.techtown.mydiettip.utils.FBRef

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.tiptab.setOnClickListener{
           it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }
        binding.talktab.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }



        return binding.root

    }



}