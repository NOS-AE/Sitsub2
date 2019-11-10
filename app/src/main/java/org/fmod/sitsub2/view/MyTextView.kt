package com.nosae.industryapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import org.fmod.sitsub2.R

/**
 * Create by NOSAE on 2019/10/31
 *
 * 支持：
 * prefix
 * sizable drawable
 * TODO prefix样式，suffix
 */
class MyTextView(context: Context, attrs: AttributeSet): TextView(context, attrs) {

    private var prefix: String = ""
        set(value) {
            text = "$value$text"
            field = value
        }

    //left, top, right, bottom
    private var mDrawableWidth = arrayOf(0,0,0,0)
    private var mDrawableHeight = arrayOf(0,0,0,0)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        prefix = a.getString(R.styleable.MyTextView_prefix) ?: ""
        mDrawableWidth[0] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableLeftWidth, 0)
        mDrawableWidth[1] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableTopWidth, 0)
        mDrawableWidth[2] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableRightWidth, 0)
        mDrawableWidth[3] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableBottomWidth, 0)
        mDrawableHeight[0] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableLeftHeight, 0)
        mDrawableHeight[1] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableTopHeight, 0)
        mDrawableHeight[2] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableRightHeight, 0)
        mDrawableHeight[3] = a.getDimensionPixelOffset(R.styleable.MyTextView_drawableBottomHeight, 0)
        a.recycle()

        setDrawableSize()
    }

    private fun setDrawableSize() {
        compoundDrawables.forEachIndexed { index, it ->
            setDrawableBounds(it, mDrawableWidth[index], mDrawableHeight[index])
        }
        setCompoundDrawables(
            compoundDrawables[0],
            compoundDrawables[1],
            compoundDrawables[2],
            compoundDrawables[3]
        )
    }

    private fun setDrawableBounds(drawable: Drawable?, width: Int, height: Int) {
        if (drawable == null) return
        val scale = drawable.intrinsicHeight / drawable.intrinsicWidth
        drawable.setBounds(0, 0, width, height)
        val bounds = drawable.bounds
        //高宽只给一个值时，自适应到原始比例
        if (bounds.right != 0 || bounds.left != 0) {
            if (bounds.right == 0) {
                bounds.right = (bounds.bottom / scale)
                drawable.bounds = bounds
            }
            if (bounds.left == 0) {
                bounds.bottom = (bounds.right * scale)
                drawable.bounds = bounds
            }
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val t = "${prefix?:""}$text"
        super.setText(t, type)
    }
}