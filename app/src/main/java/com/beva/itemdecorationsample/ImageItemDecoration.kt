package com.beva.itemdecorationsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer

class ImageItemDecoration(context: Context, private val drawableResId: Int) :
    RecyclerView.ItemDecoration() {

    private val drawable: Drawable? = ContextCompat.getDrawable(context, drawableResId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) < 3) {     //如果 position 大於等於 3，不會空出 decoration 的位置
            outRect.left = drawable?.intrinsicWidth ?: 0    //Drawable 物件本身的固有寬度
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val drawable = drawable ?: return
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (position == RecyclerView.NO_POSITION || position >= 3) {    //指定繪製位置條件
                continue
            }
            //使用 view.left view.top作為圖片的位置基準點
            val left = (view.left - (drawable.intrinsicWidth * 1.7).toInt()) + 8 //(itemview的左邊位置 減掉 圖片本身乘以1.7的寬度 在加上 8 -> 留白)
            val right = view.left - 8
            val top = view.top + (view.height - drawable.intrinsicHeight) / 2   //itemview的上邊位置 加上 (itemview高度 - 圖片高度) / 2 -> 置中
            val bottom = top + (drawable.intrinsicHeight * 1.5).toInt()         //以上面的高度作為基準在往下加 (圖片乘以1.5的高度)
            drawable.setBounds(left, top, right , bottom) //setBounds四個參數指的是drawable會被設定canvas話在指定矩形中
            drawable.draw(canvas)
            Log.d("Beva", "onDraw: left $left")
            Log.d("Beva", "onDraw: right $right")
            Log.d("Beva", "onDraw: top $top")
            Log.d("Beva", "onDraw: bottom $bottom")
        }
    }
}
