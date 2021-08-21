package com.example.myapplication.UI.Adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.UI.Listener.OnCommentListener
import com.example.myapplication.databinding.ItemCommentBinding

class Adaptador(var items:List<Comment>):RecyclerView.Adapter<Adaptador.ViewHolder>() {
    var listener: OnCommentListener?= null
    class ViewHolder(val view:ItemCommentBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemCommentBinding.inflate(inflater,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        val view=holder.view
        view.itemNombre.text=item.name
        view.itemComentario.text=item.message
 //       view.itemPhotoComment.setImageResource(R.mipmap.ic_launcher)
       Glide.with(holder.itemView).load(item.image).into(view.itemPhotoComment);


        view.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    fun newDataset(comments:List<Comment>){
        items= comments
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return items.size
    }
}