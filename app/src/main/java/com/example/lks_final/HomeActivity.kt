package com.example.lks_final

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.gabut.TrxFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btmNav : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        if (savedInstanceState == null) {
            replaceFragment(TrxFragment())
        }
        btmNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    replaceFragment(TrxFragment())
                    true
                }
                R.id.nav_account -> {
                    replaceFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Pastikan ID ini ada di XML
            .commit()
    }
}