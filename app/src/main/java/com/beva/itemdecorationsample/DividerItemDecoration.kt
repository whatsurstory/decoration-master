package com.beva.itemdecorationsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView


class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {


    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.teal_200)
    }

//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        outRect.bottom = 1
//        outRect.set(16, 16, 16, 16)     // 也可以直接在這邊設定間距
//    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft //拿recyclerview 的padding 我是設定itemview的margin 所以這邊是0
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom + (view.marginBottom) //因為有設定itemview的margin這邊要加回去不然他會貼著itemview
            val bottom = view.bottom + view.marginBottom + 1 //這邊的 1 代表這個divider的高度
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint) //直接在指定坐標繪製
            Log.d("Beva", "onDraw: left $left")
            Log.d("Beva", "onDraw: right $right")
            Log.d("Beva", "onDraw: top $top")
            Log.d("Beva", "onDraw: bottom $bottom")
        }
    }
}