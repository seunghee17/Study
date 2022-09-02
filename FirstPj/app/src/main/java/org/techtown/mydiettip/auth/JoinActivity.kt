package org.techtown.mydiettip.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
import org.techtown.mydiettip.MainActivity
import org.techtown.mydiettip.R
import org.techtown.mydiettip.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth

        binding = DataBindingUtil.setContentView(this,R.layout.activity_join)
        // Initialize Firebase Auth
        binding.joinBtn.setOnClickListener{
            var isGoTojoin=true
            val email = binding.email.text.toString()
            val password1 = binding.password.text.toString()
            val password2 = binding.passwordcheck.text.toString()
            if(email.isEmpty()){
                Toast.makeText(this,"이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoTojoin=false
            }
            if(password1.isEmpty()){
                Toast.makeText(this,"패스워드를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoTojoin=false
            }
            if(password2.isEmpty()){
                Toast.makeText(this,"패스워드를 확인해주세요", Toast.LENGTH_LONG).show()
                isGoTojoin=false
            }
            if(!password1.equals(password2)){
                Toast.makeText(this,"패스워드를 일치하여 입력해주세요", Toast.LENGTH_LONG).show()
                isGoTojoin=false
            }
            if(password1.length<6){
                Toast.makeText(this,"패스워드를 6자리 이상으로 입력해주세요", Toast.LENGTH_LONG).show()
                isGoTojoin=false
            }
            if(isGoTojoin){

                    auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this,"성공", Toast.LENGTH_LONG).show()
                                val intent = Intent(this,MainActivity::class.java)
                                //뒤로가기할때 해당 액티비티가 종료되도록
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(this,"실패",Toast.LENGTH_LONG).show()
                            }
                        }
            }
        }

    }
}