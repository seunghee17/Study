package org.techtown.mydiettip.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.techtown.mydiettip.MainActivity
import org.techtown.mydiettip.R
import org.techtown.mydiettip.board.BoardInsideActivity
import org.techtown.mydiettip.board.BoardLVAdapter
import org.techtown.mydiettip.board.BoardModel
import org.techtown.mydiettip.board.BoardWriteActivity
import org.techtown.mydiettip.contentsList.ContentRVAdapter
import org.techtown.mydiettip.databinding.FragmentTalkBinding
import org.techtown.mydiettip.utils.FBRef


class TalkFragment : Fragment() {
    private lateinit var binding:FragmentTalkBinding
    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()
    private val TAG = TalkFragment::class.java.simpleName
    private lateinit var boardRVAdapter: BoardLVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk,container, false)
        // Inflate the layout for this fragment


        boardRVAdapter = BoardLVAdapter(boardDataList)
        binding.boardListView.adapter = boardRVAdapter

        binding.boardListView.setOnItemClickListener{ parent, view, position, id->
        //1번 방법 다 다른 액티비티로 전달해줘서 받기
        /* val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("title",boardDataList[position].title)
            intent.putExtra("content",boardDataList[position].content)
            intent.putExtra("time",boardDataList[position].time)
            startActivity(intent)*/
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key",boardKeyList[position])
            startActivity(intent)

        }

        binding.writeBtn.setOnClickListener{
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }
        binding.hometab.setOnClickListener{
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }
        binding.tiptab.setOnClickListener{
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }
        getFBBoardData()
        return binding.root
    }
    private fun getFBBoardData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                boardDataList.clear()
                for(dataModel in dataSnapshot.children){
                    Log.d(TAG, dataModel.toString())

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()

                Log.d(TAG,boardDataList.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost::onCancelled",databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }


}