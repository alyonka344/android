package ru.kholmogorova.myapplication.pl

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kholmogorova.myapplication.R
import kotlin.math.abs

@SuppressLint("UseCompatLoadingForDrawables", "ResourceType")
class HistoryItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val historyImageView: ImageView
    private val removeHistoryButton: Button

    init {
        inflate(context, R.layout.history_item, this)

        historyImageView = findViewById(R.id.historyImageView)
        removeHistoryButton = findViewById(R.id.removeHistoryButton)
        setCardBackgroundColor("#4D0011".toColorInt())


        val attrsArray = intArrayOf(android.R.attr.selectableItemBackground)
        val typedArray = context.obtainStyledAttributes(attrsArray)
        val selectableItemBackground = typedArray.getDrawable(0)
        typedArray.recycle()

        foreground = selectableItemBackground
        isClickable = true

        removeHistoryButton.setOnClickListener {
            Toast.makeText(context.applicationContext, "Item deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .into(historyImageView)
    }


    fun setupParallaxEffect(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val centerY = recyclerView.height / 2
                val itemCenter = (top + bottom) / 2
                val distanceFromCenter = abs(centerY - itemCenter)
                val translationFactor = 0.5f // Настройте этот параметр

                historyImageView.translationY = distanceFromCenter * translationFactor
            }
        })
    }
}