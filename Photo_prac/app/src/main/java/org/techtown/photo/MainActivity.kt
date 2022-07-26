package org.techtown.photo

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val addPhotoButton: Button by lazy{
        findViewById<Button>(R.id.addPhotoButtoon)
    }
    private val startPhotoFrameModeButton: Button by lazy{
        findViewById<Button>(R.id.startPhotoFrameModeButton)
    }
    private val imageViewList:List<ImageView> by lazy{
        mutableListOf<ImageView>().apply{
            add(findViewById(R.id.imageView11))
            add(findViewById(R.id.imageView12))
            add(findViewById(R.id.imageView13))
            add(findViewById(R.id.imageView21))
            add(findViewById(R.id.imageView22))
            add(findViewById(R.id.imageView23))
        }
    }
    private val imageUriList: MutableList<Uri> = mutableListOf()



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()
        initStartPhotoFrameButton()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initAddPhotoButton() {
        addPhotoButton.setOnClickListener{
            when{
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED ->{
                    navigatePhotos()//권한 잘 부여되었을때 갤러리에서 사진 선택하는 기능
               }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)->{
                    showPermissionContextPopup()//교육용 파법 확인 후 권한 팝업 띄우는 기능
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)

                }
            }
        }
    }
    private fun initStartPhotoFrameButton() {
        startPhotoFrameModeButton.setOnClickListener{
            val intent = Intent(this, PhotoFrameAcivity::class.java)
            imageUriList.forEachIndexed{index, uri->
                intent.putExtra("photo$index", uri.toSting())

            }
            intent.putExtra("photoListSize", imageUriList.size)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000-> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    navigatePhotos()
                }
                else{//권한이 거부되면
                    Toast.makeText(this,"권한을 거부하셨습니다",Toast.LENGTH_SHORT).show()

                }
            }
            else -> {

            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun showPermissionContextPopup(){
        AlertDialog.Bundler(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자 액자 앱에서 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의하기"){_ , _->
                requestPermissions(arrayOf(android.Manifest.permisson.READ_EXTERNAL_STORAGE), 1000)
            }

            .setNagativeButton("취소하기"){_ , _ -> }
            .create()
            .show()
    }

    private fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,2000)
    }


}