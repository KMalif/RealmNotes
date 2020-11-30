package com.example.realmnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realmnotes.adapter.userAdapter
import com.example.realmnotes.model.user
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userAdapter: userAdapter
    lateinit var realm: Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intiview()
        bt_add.setOnClickListener {
            realm.beginTransaction()
            var count = 0
            realm.where(user::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }
            try {
                var user = realm.createObject(user::class.java)
                user.setId(count+1)
                user.setNama(et_nama.text.toString())
                user.setEmail(et_email.text.toString())
                et_nama.setText("")
                et_email.setText("")
                realm.commitTransaction()
                getallUser()
            }catch (e : RealmException){

            }
        }
        bt_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(user::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_nama.text.toString())
                it!!.setEmail(et_email.text.toString())
            }
            realm.commitTransaction()
            getallUser()
        }
        bt_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(user::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getallUser()
        }
    }

    private fun intiview() {
        rv_note.layoutManager=lm
        userAdapter= userAdapter(this)
        rv_note.adapter=userAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getallUser()
    }
    private fun getallUser(){
        realm.where(user::class.java).findAll().let {
            userAdapter.setuser(it)
        }
    }
}