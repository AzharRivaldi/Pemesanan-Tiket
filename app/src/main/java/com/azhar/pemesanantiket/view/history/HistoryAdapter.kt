package com.azhar.pemesanantiket.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.pemesanantiket.R
import com.azhar.pemesanantiket.model.ModelDatabase
import com.azhar.pemesanantiket.utils.FunctionHelper.rupiahFormat
import kotlinx.android.synthetic.main.list_item_history.view.*

/**
 * Created by Azhar Rivaldi on 30-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

class HistoryAdapter(var modelDatabase: MutableList<ModelDatabase>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun setDataAdapter(items: List<ModelDatabase>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelDatabase[position]

        when (data.keberangkatan) {
            "Jakarta" -> holder.tvKode1.text = "JKT"
            "Semarang" -> holder.tvKode1.text = "SRG"
            "Surabaya" -> holder.tvKode1.text = "SUB"
            "Bali" -> holder.tvKode1.text = "DPS"
        }

        when (data.tujuan) {
            "Jakarta" -> holder.tvKode2.text = "JKT"
            "Semarang" -> holder.tvKode2.text = "SRG"
            "Surabaya" -> holder.tvKode2.text = "SUB"
            "Bali" -> holder.tvKode2.text = "DPS"
        }

        holder.tvKelas.text = data.kelas
        holder.tvDate.text = data.tanggal
        holder.tvNama.text = data.namaPenumpang
        holder.tvKeberangkatan.text = data.keberangkatan
        holder.tvTujuan.text = data.tujuan
        holder.tvHargaTiket.text = rupiahFormat(data.hargaTiket)
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvKelas = itemView.tvKelas
        var tvDate = itemView.tvDate
        var tvNama = itemView.tvNama
        var tvHargaTiket = itemView.tvHargaTiket
        var tvKode1 = itemView.tvKode1
        var tvKode2 = itemView.tvKode2
        var tvKeberangkatan = itemView.tvKeberangkatan
        var tvTujuan = itemView.tvTujuan
    }

    fun setSwipeRemove(position: Int): ModelDatabase {
        return modelDatabase.removeAt(position)
    }

}