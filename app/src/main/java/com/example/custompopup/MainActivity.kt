package com.example.custompopup

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.custompopup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            popupBtn.setOnClickListener {
                val contentView = LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.popup, null, false)

                val popupWindow = PopupWindow(contentView,
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                    isTouchable = true
                    isFocusable = true
                    isOutsideTouchable = true
                    setBackgroundDrawable(BitmapDrawable())
                    overlapAnchor = true
                    showAsDropDown(popupBtn)
                }

                contentView.findViewById<TextView>(R.id.close).setOnClickListener {
                    Log.d(TAG, "close")
                    popupWindow.dismiss()
                }

                contentView.findViewById<Button>(R.id.content).setOnClickListener {
                    Log.d(TAG, "content")
                }

                contentView.findViewById<ViewGroup>(R.id.content_background)
                    .setOnClickListener {
                    Log.d(TAG, "content_background")
                }
            }
        }
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}