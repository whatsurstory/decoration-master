package com.beva.itemdecorationsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class TextItemDecoration(context: Context, private val text: String) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.purple_200)
        textSize = 48f
        isAntiAlias = true
        typeface = Typeface.DEFAULT_BOLD
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        //讓每個 item 在頂部留出一個文字的高度，以避免文字和 item 重疊
        outRect.top = paint.textSize.toInt()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i) //得到目前 item 的位置
            val position = parent.getChildAdapterPosition(view)
            if (position == RecyclerView.NO_POSITION) {
                continue
            }
            val textWidth = paint.measureText(text)
//            val x = parent.paddingLeft.toFloat() + (parent.width - parent.paddingLeft - parent.paddingRight - textWidth) / 2  // -> 針對parent的寬度置中
            val x = view.left.toFloat() + (view.width - textWidth) / 2  // -> 針對itemview自己的寬度置中
            val y = view.top + (paint.textSize - view.marginTop - view.marginBottom)
            canvas.drawText(text, x, y, paint) //繪製文字
            Log.d("Beva", "onDraw: x $x")
            Log.d("Beva", "onDraw: y $y")
        }
    }
}