package com.example.mohammed_2284.simpleapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mohammed_2284.simpleapp.retrofit.APIKindaStuff
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPOST.setOnClickListener {
            val jsonObj = JsonObject()
            jsonObj.addProperty("title", "rhythm")
            jsonObj.addProperty("singer", "meee")
            jsonObj.addProperty("text", "Jack and jill went up the hill to fetch a pail of water!")
            //  POST demo
            APIKindaStuff
                    .service
                    .getVectors(jsonObj)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: POST msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }

        btnGET.setOnClickListener {
            APIKindaStuff
                    .service
                    .greetUser("Audhil")
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }
    }
}