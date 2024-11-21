package com.example.androidpermission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val contacts: ArrayList<Contact>,
) : RecyclerView.Adapter<RecyclerAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val numPhoneTV: TextView = itemView.findViewById(R.id.numPhoneTV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTV.text = contact.name
        holder.numPhoneTV.text = contact.numPhone
    }

    override fun getItemCount(): Int = contacts.size
}