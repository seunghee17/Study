package org.techtown.mydiettip.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.techtown.mydiettip.R
import org.techtown.mydiettip.comment.CommentLVAdapter
import org.techtown.mydiettip.comment.CommentModel
import org.techtown.mydiettip.databinding.ActivityBoardInsideBinding
import org.techtown.mydiettip.utils.FBAuth
import org.techtown.mydiettip.utils.FBRef
import java.lang.Exception

class BoardInsideActivity : AppCompatActivity() {
    private val TAG = BoardInsideActivity::class.java.simpleName
    private lateinit var binding : ActivityBoardInsideBinding
    private lateinit var key : String
    private val commentDataList = mutableListOf<CommentModel>()
    private lateinit var commentAdapter : CommentLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_inside)

        binding.boardSettingIcon.setOnClickListener{

            showDialog()
        }


        /*val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        binding.titleArea.text = title
        binding.contentArea.text = content
        binding.timeArea.text = time*/

        key = intent.getStringExtra("key").toString()
        getBoardData(key)

        binding.commentBtn.setOnClickListener{
            insertComment(key)
        }
        commentAdapter = CommentLVAdapter(commentDataList)
        binding.commentLV.adapter = commentAdapter
        getCommentData(key)


    }
    fun getCommentData(key : String){
        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                commentDataList.clear()

                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(CommentModel::class.java)
                    commentDataList.add(item!!)
                }
                commentAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost::onCancelled",databaseError.toException())
            }
        }
        FBRef.commentRef.child(key).addValueEventListener(postListener)

    }

    fun insertComment(key:String){
        FBRef.commentRef
             .child(key)
             .push()
             .setValue(
                 CommentModel(binding.commentArea.text.toString(),
                     FBAuth.getTime()
             ))
        Toast.makeText(this,"댓글 입력 완료", Toast.LENGTH_LONG).show()
        binding.commentArea.setText("")
    }

    private fun showDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 삭제")
        val alertDialog = mBuilder.show()

        alertDialog.findViewById<Button>(R.id.removeBtn)?.setOnClickListener{
            FBRef.boardRef.child(key).removeValue()
            Toast.makeText(this,"삭제 완료", Toast.LENGTH_LONG).show()

            finish()

        }

    }
    private fun getBoardData(key:String){
        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try{
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    Log.d(TAG, dataModel!!.title)
                    binding.titleArea.text = dataModel!!.title
                    binding.contentArea.text = dataModel!!.content
                    binding.timeArea.text = dataModel!!.time
                }catch(e:Exception){
                    Log.d(TAG, "삭제완료")
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG,"loadPost:onCancelled",databaseError.toException())
            }

        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}