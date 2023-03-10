package org.techtown.memo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context, name:String, version:Int): SQLiteOpenHelper(context, name, null, version) {
    //이 메서드의 파라미터로 우리가 사용할 데이터베이스가 전달된다
    //이 메서드에 테이블 생성 쿼리를 작성하면 된다
    override fun onCreate(db: SQLiteDatabase?) {
        val create="create table memo(" +
                "no integer primary key," +
                "content text," +
                "datetime integer" +
                ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertMemo(memo:Memo){
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)
        val wd = writableDatabase
        wd.insert("memo", null, values)
        wd.close()
    }
    fun selectMemo():MutableList<Memo>{
        val list = mutableListOf<Memo>()
        val select = "select * from memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)
        while(cursor.moveToNext())
        {
            val no = cursor.getLong(cursor.getColumnIndexOrThrow("no"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            val datetime = cursor.getLong(cursor.getColumnIndexOrThrow("datetime"))
            list.add(Memo(no, content, datetime))
        }
        cursor.close()
        rd.close()
        return list
    }
    fun updateMemo(memo:Memo){
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)
        val wd = writableDatabase
        wd.update("memo", values, "no={$memo.no}", null)
        wd.close()
    }
    fun deleteMemo(memo:Memo){
        val delete = "delete from memo where no =${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}

data class Memo(var no: Long?, var content:String, var datetime:Long)