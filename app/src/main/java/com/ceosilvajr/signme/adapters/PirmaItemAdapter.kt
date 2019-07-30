package com.ceosilvajr.signme.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceosilvajr.signme.R
import com.ceosilvajr.signme.extensions.base64ToBitmap
import com.ceosilvajr.signme.models.Pirma
import kotlinx.android.synthetic.main.item_pirma.view.*
import java.util.*

/**
 * @author ceosilvajr@gmail.com
 */
class PirmaItemAdapter(private val items: ArrayList<Pirma>) : RecyclerView.Adapter<PirmaItemAdapter.AdapterViewHolder>() {

    private lateinit var context: Context
    private lateinit var listener: Listener

    fun init(context: Context, listener: Listener) {
        this.context = context
        this.listener = listener
    }

    fun updateList(items: ArrayList<Pirma>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        if (!::context.isInitialized || !::listener.isInitialized) throw RuntimeException()
        return AdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pirma, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val model = items[position]
        holder.itemView.setOnClickListener { listener.onPirmaItemClicked(model) }
        holder.displayView(model)
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.iv_image!!
        private val id = view.tv_id!!
        private val time = view.tv_time!!

        fun displayView(model: Pirma) {
            image.setImageBitmap(model.encodedBase64Image.base64ToBitmap())
            id.text = model.id
            time.text = model.date.toString()
        }
    }

    interface Listener {
        fun onPirmaItemClicked(pirma: Pirma)
    }
}