package com.azhar.pemesanantiket.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

@Entity(tableName = "tbl_travel")
class ModelDatabase : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid = 0

    @ColumnInfo(name = "nama_penumpang")
    lateinit var namaPenumpang: String

    @ColumnInfo(name = "keberangkatan")
    lateinit var keberangkatan: String

    @ColumnInfo(name = "tujuan")
    lateinit var tujuan: String

    @ColumnInfo(name = "tanggal")
    lateinit var tanggal: String

    @ColumnInfo(name = "harga_tiket")
    var hargaTiket = 0

    @ColumnInfo(name = "anak_anak")
    var anakAnak = 0

    @ColumnInfo(name = "dewasa")
    var dewasa = 0

    @ColumnInfo(name = "nomor_telepon")
    lateinit var nomorTelepon: String

    @ColumnInfo(name = "kelas")
    lateinit var kelas: String

    @ColumnInfo(name = "status")
    lateinit var status: String
}