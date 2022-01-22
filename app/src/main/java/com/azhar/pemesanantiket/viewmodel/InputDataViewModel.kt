package com.azhar.pemesanantiket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azhar.pemesanantiket.database.DatabaseClient.Companion.getInstance
import com.azhar.pemesanantiket.database.dao.DatabaseDao
import com.azhar.pemesanantiket.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

class InputDataViewModel(application: Application) : AndroidViewModel(application) {
    var databaseDao: DatabaseDao?

    fun addDataPemesan(
        nama_penumpang: String, keberangkatan: String,
        tujuan: String, tanggal: String, nomor_telepon: String,
        anak_anak: Int, dewasa: Int, harga_tiket: Int, kelas: String, status: String
    ) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase()
            modelDatabase.namaPenumpang = nama_penumpang
            modelDatabase.keberangkatan = keberangkatan
            modelDatabase.tujuan = tujuan
            modelDatabase.tanggal = tanggal
            modelDatabase.nomorTelepon = nomor_telepon
            modelDatabase.anakAnak = anak_anak
            modelDatabase.dewasa = dewasa
            modelDatabase.hargaTiket = harga_tiket
            modelDatabase.kelas = kelas
            modelDatabase.status = status
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application).appDatabase.databaseDao()
    }

}