package com.example.lks_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username : EditText = findViewById(R.id.username)
        val password : EditText = findViewById(R.id.password)
        val daftar : TextView = findViewById(R.id.txtDaftar)
        val login : Button = findViewById(R.id.btnLogin)
        login.setOnClickListener {
            val LoginBody = LoginRequest(username.text.toString(), password.text.toString())
            RetrofitClient.instance.login(LoginBody).enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
                {
                    val data = response.body()
                    if(response.isSuccessful){
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        Toast.makeText(applicationContext,"Login berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("LoginError", "Error: ${t.message}")
                    Toast.makeText(applicationContext, "Login GAGAL: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}