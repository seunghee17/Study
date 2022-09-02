package org.techtown.mydiettip.contentsList

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.techtown.mydiettip.R

class ContentsListActivity : AppCompatActivity() {
    lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val rvAdapter = ContentRVAdapter(baseContext, items)

        // Write a message to the database
        val database = Firebase.database
        val category = intent.getStringExtra("category")

        if(category == "category1"){
            myRef = database.getReference("contents")


        }else if(category=="category2"){
            myRef = database.getReference("contents2")
//여기만 추가하면 구축완료

        }
        else if(category=="category3"){
            myRef = database.getReference("contents3")
        }
        else if(category=="category4"){
            myRef = database.getReference("contents4")
        }
        else if(category=="category5"){
            myRef = database.getReference("contents5")
        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d("ContentListActivity", dataModel.toString())
                    val item= dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)

                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.rv)



        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,2)
        rvAdapter.itemClick = object : ContentRVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, items[position].title,Toast.LENGTH_LONG).show()
                val intent = Intent(this@ContentsListActivity, ContentShowActivity::class.java)
                intent.putExtra("url", items[position].webUrl)
                startActivity(intent)
            }
            /*myRef.push().setValue(
            ContentModel("두부유부초밥","https://postfiles.pstatic.net/MjAyMjA3MzFfMjgz/MDAxNjU5MjIzMjE5Njk1.FvadfA_ATz9Ojotvnu7y2P-0F1klMkp6GM6wnz7eXfUg.JJX4PQB2bgy7wwgMTptLN3dhSxb_rpaKWkt6t3qk7HUg.JPEG.kkalgid1/1.jpg?type=w966","https://blog.naver.com/jin5194/222856848695?isInf=true")
        )
        myRef.push().setValue(
            ContentModel("키토김밥","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb1pcPi%2Fbtq872WILOZ%2Fu4XZkctFIli5zHK1MoPaL1%2Fimg.jpg","https://soha1992.tistory.com/entry/%ED%82%A4%ED%86%A0%EA%B9%80%EB%B0%A5-%ED%84%B0%EC%A7%80%EC%A7%80-%EC%95%8A%EA%B2%8C-%EB%A7%8C%EB%93%9C%EB%8A%94-%EC%84%B8-%EA%B0%80%EC%A7%80-%EA%BF%80%ED%8C%81-%EB%8B%B9%EA%B7%BC-%EB%9D%BC%ED%8E%98")
        )
        myRef.push().setValue(
            ContentModel("양배추 덮밥","https://recipe1.ezmember.co.kr/cache/recipe/2020/04/18/0e49d213764f08bae396e4bcf2a9eb2f1.jpg","https://m.10000recipe.com/recipe/6930961")
        )
        myRef.push().setValue(
            ContentModel("토마토 계란 볶음","https://recipe1.ezmember.co.kr/cache/recipe/2016/08/26/555d824ba38568d7593032c1513fd9ed1.jpg","https://m.10000recipe.com/recipe/6855392")

        )
        myRef.push().setValue(
            ContentModel("계란 샌드위치","https://i0.wp.com/i1.ytimg.com/vi/XWHNrXJxmnI/hqdefault.jpg?w=1280&ssl=1","https://foodiebadge.com/%EB%B9%B5%EC%97%86%EC%9D%B4-%EA%B3%84%EB%9E%80%EC%9C%BC%EB%A1%9C-%EC%83%8C%EB%93%9C%EC%9C%84%EC%B9%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-%EA%B3%84%EB%9E%80%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EA%B0%90/")

        )
        myRef.push().setValue(
            ContentModel("양배추 샐러드","https://recipe1.ezmember.co.kr/cache/recipe/2022/05/20/9ee9708ce38034647bde679cf22094761.jpg","https://m.10000recipe.com/recipe/6980535")

        )
        myRef.push().setValue(
            ContentModel("양배추 쌈","https://recipe1.ezmember.co.kr/cache/recipe/2015/06/10/bda85722382329c24c54c2d4dd9a6a601.jpg","https://www.10000recipe.com/recipe/6827518")

        )
        myRef.push().setValue(
            ContentModel("포두부 오리고기 부리또","https://recipe1.ezmember.co.kr/cache/recipe/2021/01/22/090b324e94835470e842d553e4ca4e5d1.jpg","https://www.10000recipe.com/recipe/6951792")

        )*/
            /*items.add(ContentModel("두부유부초밥","https://postfiles.pstatic.net/MjAyMjA3MzFfMjgz/MDAxNjU5MjIzMjE5Njk1.FvadfA_ATz9Ojotvnu7y2P-0F1klMkp6GM6wnz7eXfUg.JJX4PQB2bgy7wwgMTptLN3dhSxb_rpaKWkt6t3qk7HUg.JPEG.kkalgid1/1.jpg?type=w966","https://blog.naver.com/jin5194/222856848695?isInf=true"))
        items.add(ContentModel("키토김밥","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb1pcPi%2Fbtq872WILOZ%2Fu4XZkctFIli5zHK1MoPaL1%2Fimg.jpg","https://soha1992.tistory.com/entry/%ED%82%A4%ED%86%A0%EA%B9%80%EB%B0%A5-%ED%84%B0%EC%A7%80%EC%A7%80-%EC%95%8A%EA%B2%8C-%EB%A7%8C%EB%93%9C%EB%8A%94-%EC%84%B8-%EA%B0%80%EC%A7%80-%EA%BF%80%ED%8C%81-%EB%8B%B9%EA%B7%BC-%EB%9D%BC%ED%8E%98"))
        items.add(ContentModel("양배추 덮밥","https://recipe1.ezmember.co.kr/cache/recipe/2020/04/18/0e49d213764f08bae396e4bcf2a9eb2f1.jpg","https://m.10000recipe.com/recipe/6930961"))
        items.add(ContentModel("토마토 계란 볶음","https://recipe1.ezmember.co.kr/cache/recipe/2016/08/26/555d824ba38568d7593032c1513fd9ed1.jpg","https://m.10000recipe.com/recipe/6855392"))
        items.add(ContentModel("계란 샌드위치","https://i0.wp.com/i1.ytimg.com/vi/XWHNrXJxmnI/hqdefault.jpg?w=1280&ssl=1","https://foodiebadge.com/%EB%B9%B5%EC%97%86%EC%9D%B4-%EA%B3%84%EB%9E%80%EC%9C%BC%EB%A1%9C-%EC%83%8C%EB%93%9C%EC%9C%84%EC%B9%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-%EA%B3%84%EB%9E%80%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EA%B0%90/"))
        items.add(ContentModel("양배추 샐러드","https://recipe1.ezmember.co.kr/cache/recipe/2022/05/20/9ee9708ce38034647bde679cf22094761.jpg","https://m.10000recipe.com/recipe/6980535"))
        items.add(ContentModel("양배추 쌈","https://recipe1.ezmember.co.kr/cache/recipe/2015/06/10/bda85722382329c24c54c2d4dd9a6a601.jpg","https://www.10000recipe.com/recipe/6827518"))
        items.add(ContentModel("포두부 오리고기 부리또","https://recipe1.ezmember.co.kr/cache/recipe/2021/01/22/090b324e94835470e842d553e4ca4e5d1.jpg","https://www.10000recipe.com/recipe/6951792"))*/
        }
        /*val myRef2 = database.getReference("contents2")
        myRef2.push().setValue(
            ContentModel("두부유부초밥","https://postfiles.pstatic.net/MjAyMjA3MzFfMjgz/MDAxNjU5MjIzMjE5Njk1.FvadfA_ATz9Ojotvnu7y2P-0F1klMkp6GM6wnz7eXfUg.JJX4PQB2bgy7wwgMTptLN3dhSxb_rpaKWkt6t3qk7HUg.JPEG.kkalgid1/1.jpg?type=w966","https://blog.naver.com/jin5194/222856848695?isInf=true")
        )
        myRef2.push().setValue(
            ContentModel("키토김밥","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb1pcPi%2Fbtq872WILOZ%2Fu4XZkctFIli5zHK1MoPaL1%2Fimg.jpg","https://soha1992.tistory.com/entry/%ED%82%A4%ED%86%A0%EA%B9%80%EB%B0%A5-%ED%84%B0%EC%A7%80%EC%A7%80-%EC%95%8A%EA%B2%8C-%EB%A7%8C%EB%93%9C%EB%8A%94-%EC%84%B8-%EA%B0%80%EC%A7%80-%EA%BF%80%ED%8C%81-%EB%8B%B9%EA%B7%BC-%EB%9D%BC%ED%8E%98")
        )
        myRef2.push().setValue(
            ContentModel("양배추 덮밥","https://recipe1.ezmember.co.kr/cache/recipe/2020/04/18/0e49d213764f08bae396e4bcf2a9eb2f1.jpg","https://m.10000recipe.com/recipe/6930961")
        )
        myRef2.push().setValue(
            ContentModel("토마토 계란 볶음","https://recipe1.ezmember.co.kr/cache/recipe/2016/08/26/555d824ba38568d7593032c1513fd9ed1.jpg","https://m.10000recipe.com/recipe/6855392")

        )*/

        /*val myRef3 = database.getReference("contents3")
        myRef3.push().setValue(
            ContentModel("데드리프트","https://contentsfree.com/wp-content/uploads/2019/10/%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8%EB%9E%80.png","https://contentsfree.com/%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8/")
        )
        myRef3.push().setValue(
            ContentModel("랫풀다운","https://i0.wp.com/0k-cal.com/wp-content/uploads/2021/09/%EB%9E%AB%ED%92%80%EB%8B%A4%EC%9A%B4.jpg?w=720&ssl=1","https://0k-cal.com/%EB%9E%AB%ED%92%80%EB%8B%A4%EC%9A%B4-%ED%95%98%EB%8A%94%EB%B2%95-%EC%9E%90%EC%84%B8-%EA%B7%B8%EB%A6%BD/")
        )

        myRef3.push().setValue(
            ContentModel("아웃타이","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FecQtYN%2Fbtq709uSKLw%2FMKW8HnFAPXBZr3sDTSHUXK%2Fimg.png","https://fitnessoo.tistory.com/30")

        )*/
        /*val myRef4 = database.getReference("contents4")
        myRef4.push().setValue(
            ContentModel("단백질 보충제","https://m.spomax.kr/data/goods/15/07/28/1000001629/1000001629_detail_073.jpg","https://m.spomax.kr/goods/goods_view.php?goodsNo=1000001629")
        )
        myRef4.push().setValue(
            ContentModel("아르니긴","https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9977EF4E5DF346470D","https://www.coupang.com/vp/products/27897?itemId=8229327143&vendorItemId=78563800726&pickType=COU_PICK&q=%EC%95%84%EB%A5%B4%EB%8B%88%EA%B8%B4&itemsCount=36&searchId=f9b5a91f67d143949129f3b97579fe4a&rank=1&isAddedCart=")
        )
        val myRef5 = database.getReference("contents5")
        myRef5.push().setValue(
            ContentModel("젝시믹스 레깅스","https://shop2.daumcdn.net/thumb/R500x500/?fname=http%3A%2F%2Fshop2.daumcdn.net%2Fshophow%2Fp%2FG11308270459.jpg%3Fut%3D20210930122558","https://m.shoppinghow.kakao.com/m/search/q/%EC%A0%9D%EC%8B%9C%EB%AF%B9%EC%8A%A4%20%EB%A0%88%EA%B9%85%EC%8A%A4")
        )
        myRef5.push().setValue(
            ContentModel("안다르 레깅스","https://cafe24img.poxo.com/andar01/web/product/extra/big/202112/b0a8bd968482b0448de9522af24b13f0.jpg","https://andar.co.kr/product/detail.html?product_no=6748&cate_no=350&display_group=1")
        )*/

    }

}