package com.example.realmnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realmnotes.R
import com.example.realmnotes.model.user

class userAdapter (val context: Context): RecyclerView.Adapter<userAdapter.userViewHolder>() {
    private var users:MutableList<user> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        return userViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_template, parent, false))
    }

    override fun getItemCount(): Int {
    return users.size
    }

    override fun onBindViewHolder(holder: userAdapter.userViewHolder, position: Int) {
    holder.bindmodel(users[position])
        }


    fun setuser (data:List<user>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()

    }

    inner class userViewHolder (item:View): RecyclerView.ViewHolder(item){
        val txtId : TextView = item.findViewById(R.id.tv_id)
        val txtNama :TextView = item.findViewById(R.id.tv_nama)
        val txtEmail :TextView = item.findViewById(R.id.tv_email)
        fun bindmodel(u:user){

                txtId.text = u.getId().toString()
                txtNama.text = u.getNama()
                txtEmail.text = u.getEmail()

        }
    }
}