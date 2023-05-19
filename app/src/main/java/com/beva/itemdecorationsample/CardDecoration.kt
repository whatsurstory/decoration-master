package com.beva.itemdecorationsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class CardDecoration(private val context: Context, @DrawableRes private val dividerRes: Int, @ColorRes dividerColorRes: Int) :
    ItemDecoration() {

    private val dividerBitmap: Drawable? = ContextCompat.getDrawable(context, dividerRes)?.mutate()
    //.mutate() -> 可以對帶進來的資源進行自定義的修改，而不會影響其他使用同一個 Drawable的element。
    //創建一個該繪製資源的可變副本，以便對其進行自定義的修改而不影響其他地方使用同一繪製資源的元素

    init {
        dividerBitmap?.setTint(ContextCompat.getColor(context, dividerColorRes))
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val top = child.top - dpToPx(12)    // plus will let the icon down ; minus will let it upper
            val left = child.left + dpToPx(12)  // plus will let the icon moving to right ; minus will let it to left
            dividerBitmap?.toBitmap()?.let {
                c.drawBitmap(it, left.toFloat(), top.toFloat(), null) //指定圖像在畫布上的位置。x 和 y 坐標分別代表畫布的左上角為原點（0, 0），x 軸向右增加，y 軸向下增加
            }
        }
    }

    //根據當前設備的螢幕密度將dp值轉換為對應的pixel值，並返回一個Integer。這樣就可以在佈局或繪製過程中使用這個pixel值，以確保在不同設備上顯示正確的尺寸
    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,        // dp單位
            dp.toFloat(),                       // 數值型別
            context.resources.displayMetrics    //提供了當前設備的屏幕密度等信息，用於進行轉換計算
        ).toInt()
    }
}