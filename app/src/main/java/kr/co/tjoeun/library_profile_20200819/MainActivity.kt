package kr.co.tjoeun.library_profile_20200819

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        callBtn.setOnClickListener {
            /*
            * 전화 기능을 사용 가능 상태인지? 확인
            * => 사용 가능할때의 코드 + 불가능할때의 코드
            * 실제로 권한 요청
            */
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {

//                    권한이 승인된 상태일때 실행할 코드
                    val myUri = Uri.parse("tel:010-3469-9369")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }


                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                    권한이 최종 거부되었을대 실행할 코드
                    Toast.makeText(mContext, "전화권한이 거부되어 연결이 불가능합니다.",  Toast.LENGTH_SHORT).show()
                }
            }
//                완성된 행동 방침 을 가지고 권한 실제 요청
            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("권한이 불가능합니다.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()


//            전화를 바로 연결처리 (ACTION_CALL)
       }

        profilePhotoImg.setOnClickListener{

//            사진을 크게 보는 별개의 액티비티로 이동
            val myIntent = Intent(mContext, ViewProfilePhotoActivity::class.java)
            startActivity(myIntent)

        }
    }

    override fun setValues() {

//        Glide로 웹상의 이미지를 프사로 반영
        Glide.with(mContext).load("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/170924_%ED%91%B8%EB%93%9C%ED%8A%B8%EB%9F%AD%EC%9D%B4%EB%B2%A4%ED%8A%B8.jpg/250px-170924_%ED%91%B8%EB%93%9C%ED%8A%B8%EB%9F%AD%EC%9D%B4%EB%B2%A4%ED%8A%B8.jpg").into(profilePhotoImg)
    }
}