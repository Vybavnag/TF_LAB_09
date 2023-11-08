package com.driuft.sharedlifecycle

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _sharedPreferences: SharedPreferences
    private fun unregisterListener() {
        _sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        _sharedPreferences = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            // replace "onCreate" with the name of the method you're saving
            putString(getString(R.string.current_lifecycle_state), "onCreate")
            apply()
        }

        // Read the state
        val currentState = _sharedPreferences.getString(
            getString(R.string.current_lifecycle_state),
            resources.getString(R.string.default_state)
        ) ?: resources.getString(R.string.default_state)



        // Log statement at debug level
        Log.d("Lifecycle: ", "onCreate")
        // Toast
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val listener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            Toast.makeText(
                this,
                sharedPreferences.getString(
                    key,
                    resources.getString(R.string.default_state)
                ),
                Toast.LENGTH_SHORT
            ).show()
        }
    override fun onStart() {
        super.onStart()
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onStart")
            apply()
        }
        // Log statement at debug level
        Log.d("Lifecycle: ", "onStart")
        // Toast
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onStart")
            apply()
        }
        // Log statement at debug level
        Log.d("Lifecycle: ", "onResume")
        // Toast
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onDestroy")
            apply()
        }
        // Log statement at debug level
        Log.d("Lifecycle: ", "onStart")
        // Toast
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
        unregisterListener()
    }
}