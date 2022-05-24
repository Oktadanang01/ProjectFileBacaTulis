package com.example.projectfilebacatulisdanok

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnRemove = findViewById<Button>(R.id.btnRemove)

        //untuk menyimpan file
        btnSave.setOnClickListener(View.OnClickListener {
            val file: String = fileName.text.toString()
            val data: String = fileData.text.toString()

            val fileOutputStream: FileOutputStream

            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            //untuk notifikasi file jka berhasil disimpan
            Toast.makeText(applicationContext, "data berhasil disimpan", Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()

        })

        //untuk membaca fileText
        btnView.setOnClickListener(View.OnClickListener {

            val filename = fileName.text.toString()
            if (filename.toString() != null && filename.toString().trim() != "") {
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //menampilkan text pada editText fileData
                fileData.setText(stringBuilder.toString()).toString()
            } else {
                Toast.makeText(applicationContext, "file name cannot be blank", Toast.LENGTH_LONG)
                    .show()
            }
        })

        //untuk menghapus file
        btnRemove.setOnClickListener(View.OnClickListener {

        })
    }
}