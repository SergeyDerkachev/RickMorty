package ru.softwarefree.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_list -> {
                    replaceFragment(ListFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_favorites -> {
                    replaceFragment(FavoritesFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = R.id.action_list
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}