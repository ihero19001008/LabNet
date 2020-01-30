package com.ankit.labnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnShow.setOnClickListener {
            val username = edtUser.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            sendPostRequest(username,password)
        }

    }
   private fun sendPostRequest(userName:String, password:String) {

        var reqParam = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")

        val mURL = URL("http://test.loadimpact.com/login.php?$reqParam")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is POST
            requestMethod = "POST"

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
                Toast.makeText(this@LoginActivity,response,Toast.LENGTH_LONG).show()
            }
        }
    }
}
