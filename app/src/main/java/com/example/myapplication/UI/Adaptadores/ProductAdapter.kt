package com.example.myapplication.UI.Adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.UI.Listener.OnProductListener
import com.example.myapplication.Data.Models.Producto

import com.example.myapplication.databinding.ItemProductBinding

class ProductAdapter(var items:List<Producto>):RecyclerView.Adapter<ProductAdapter.ViewHolder> (){
    var listener: OnProductListener?= null
    class ViewHolder(val view: ItemProductBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemProductBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        val view=holder.view
        view.itemProductNombre.text=item.name
        view.itemProductPrice.text=item.price
      //  view.itemPhotoComment.setImageResource(R.mipmap.ic_launcher)
       Glide.with(holder.itemView).load(item.image).into(view.itemImageProduct);
        view.root.setOnClickListener{
           listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun newDataset(productos:List<Producto>){
        items= productos
        notifyDataSetChanged()
    }
}