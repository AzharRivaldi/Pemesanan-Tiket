package com.azhar.pemesanantiket.view.input

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.azhar.pemesanantiket.R
import com.azhar.pemesanantiket.viewmodel.InputDataViewModel
import kotlinx.android.synthetic.main.activity_input_data.*
import java.text.SimpleDateFormat
import java.util.*

class DataPesawatActivity : AppCompatActivity() {
    val strAsal = arrayOf("Jakarta", "Semarang", "Surabaya", "Bali")
    val strTujuan = arrayOf("Jakarta", "Semarang", "Surabaya", "Bali")
    val strKelas = arrayOf("Eksekutif", "Bisnis", "Ekonomi")
    lateinit var inputDataViewModel: InputDataViewModel
    lateinit var sAsal: String
    lateinit var sTujuan: String
    lateinit var sTanggal: String
    lateinit var sNama: String
    lateinit var sTelp: String
    lateinit var sKelas: String
    var hargaDewasa = 0
    var hargaAnak = 0
    var hargaKelas = 0
    var hargaTotalDewasa = 0
    var hargaTotalAnak = 0
    var hargaTotal = 0
    var jmlDewasa = 0
    var jmlAnak = 0
    var countAnak = 0
    var countDewasa = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)
        setStatusBar()
        setToolbar()
        setInitView()
        setViewModel()
        setSpinnerAdapter()
        setJmlPenumpang()
        setInputData()
    }

    private fun setViewModel() {
        inputDataViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.application)
        )
            .get(InputDataViewModel::class.java)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setInitView() {
        inputTanggal.setOnClickListener { view: View? ->
            val tanggalJemput = Calendar.getInstance()
            val date =
                OnDateSetListener { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    tanggalJemput[Calendar.YEAR] = year
                    tanggalJemput[Calendar.MONTH] = monthOfYear
                    tanggalJemput[Calendar.DAY_OF_MONTH] = dayOfMonth
                    val strFormatDefault = "d MMMM yyyy"
                    val simpleDateFormat = SimpleDateFormat(strFormatDefault, Locale.getDefault())
                    inputTanggal.setText(simpleDateFormat.format(tanggalJemput.time))
                }
            DatePickerDialog(
                this@DataPesawatActivity, date,
                tanggalJemput[Calendar.YEAR],
                tanggalJemput[Calendar.MONTH],
                tanggalJemput[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    private fun setSpinnerAdapter() {
        val adapterAsal =
            ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, strAsal)
        adapterAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBerangkat.adapter = adapterAsal

        val adapterTujuan =
            ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, strTujuan)
        adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTujuan.adapter = adapterTujuan

        val adapterKelas =
            ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, strKelas)
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spKelas.adapter = adapterKelas

        spBerangkat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sAsal = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sTujuan = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spKelas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sKelas = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setJmlPenumpang() {
        //add anak
        imageAdd1.setOnClickListener {
            countAnak = countAnak + 1
            tvJmlAnak.text = countAnak.toString()
        }

        //min anak
        imageMinus1.setOnClickListener {
            if (countAnak > 0) {
                countAnak = countAnak - 1
                tvJmlAnak.text = countAnak.toString()
            }
        }

        //add dewasa
        imageAdd2.setOnClickListener {
            countDewasa = countDewasa + 1
            tvJmlDewasa.text = countDewasa.toString()
        }

        //min dewasa
        imageMinus2.setOnClickListener {
            if (countDewasa > 0) {
                countDewasa = countDewasa - 1
                tvJmlDewasa.text = countDewasa.toString()
            }
        }
    }

    private fun setInputData() {
        btnCheckout.setOnClickListener { v: View? ->
            setPerhitunganHargaTiket()
            sNama = inputNama.text.toString()
            sTanggal = inputTanggal.text.toString()
            sTelp = inputTanggal.text.toString()
            if (sNama.isEmpty() || sTanggal.isEmpty() || sTelp.isEmpty()
                || sAsal.isEmpty() || sTujuan.isEmpty() || countDewasa == 0
                || hargaTotal == 0 || sKelas.isEmpty()
            ) {
                Toast.makeText(
                    this@DataPesawatActivity,
                    "Mohon lengkapi data pemesanan!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (sAsal == "Jakarta" && sTujuan == "Jakarta"
                    || sAsal == "Semarang" && sTujuan == "Semarang"
                    || sAsal == "Surabaya" && sTujuan == "Surabaya"
                    || sAsal == "Bali" && sTujuan == "Bali"
                ) {
                    Toast.makeText(
                        this@DataPesawatActivity,
                        "Asal dan Tujuan tidak boleh sama!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    inputDataViewModel.addDataPemesan(
                        sNama,
                        sAsal,
                        sTujuan,
                        sTanggal,
                        sTelp,
                        countAnak,
                        countDewasa,
                        hargaTotal,
                        sKelas,
                        "1"
                    )
                    Toast.makeText(
                        this@DataPesawatActivity,
                        "Booking Tiket berhasil, cek di menu riwayat",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }

    private fun setPerhitunganHargaTiket() {
        //set Asal & Tujuan
        if (sAsal == "Jakarta" && sTujuan == "Semarang") {
            hargaDewasa = 1200000
            hargaAnak = 120000
        } else if (sAsal == "Jakarta" && sTujuan == "Surabaya") {
            hargaDewasa = 1500000
            hargaAnak = 140000
        } else if (sAsal == "Jakarta" && sTujuan == "Bali") {
            hargaDewasa = 1800000
            hargaAnak = 160000
        } else if (sAsal == "Semarang" && sTujuan == "Jakarta") {
            hargaDewasa = 1200000
            hargaAnak = 120000
        } else if (sAsal == "Semarang" && sTujuan == "Surabaya") {
            hargaDewasa = 1300000
            hargaAnak = 140000
        } else if (sAsal == "Semarang" && sTujuan == "Bali") {
            hargaDewasa = 1500000
            hargaAnak = 160000
        } else if (sAsal == "Surabaya" && sTujuan == "Jakarta") {
            hargaDewasa = 1500000
            hargaAnak = 160000
        } else if (sAsal == "Surabaya" && sTujuan == "Semarang") {
            hargaDewasa = 1300000
            hargaAnak = 140000
        } else if (sAsal == "Surabaya" && sTujuan == "Bali") {
            hargaDewasa = 1300000
            hargaAnak = 120000
        } else if (sAsal == "Bali" && sTujuan == "Jakarta") {
            hargaDewasa = 1800000
            hargaAnak = 160000
        } else if (sAsal == "Bali" && sTujuan == "Semarang") {
            hargaDewasa = 1500000
            hargaAnak = 140000
        } else if (sAsal == "Bali" && sTujuan == "Surabaya") {
            hargaDewasa = 1300000
            hargaAnak = 120000
        }

        //set Kelas Penumpang
        if (sKelas == "Eksekutif") {
            hargaKelas = 500000
        } else if (sKelas.equals("Bisnis", ignoreCase = true)) {
            hargaKelas = 500000
        } else if (sKelas.equals("Ekonomi", ignoreCase = true)) {
            hargaKelas = 100000
        }

        jmlDewasa = countDewasa
        jmlAnak = countAnak
        hargaTotalDewasa = jmlDewasa * hargaDewasa
        hargaTotalAnak = jmlAnak * hargaAnak
        hargaTotal = hargaTotalDewasa + hargaTotalAnak + hargaKelas
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}