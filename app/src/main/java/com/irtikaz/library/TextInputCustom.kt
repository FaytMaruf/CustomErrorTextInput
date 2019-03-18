package com.irtikaz.library

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView



class TextInputCustom(context: Context, attrs: AttributeSet): TextInputLayout(context, attrs) {

    private var errorGravity: Int = 0

    init {
        context.theme.obtainStyledAttributes( attrs, R.styleable.TextInputCustom, 0, 0)
                .apply {
                    errorGravity = getInteger(R.styleable.TextInputCustom_errorGravity, 0)
                }.recycle()
    }

    override fun setError(error: CharSequence?) {
        super.setError(error)

        if (!isErrorEnabled) return
        try{
            val errorField = TextInputLayout::class.java.getDeclaredField("mErrorView")
            errorField.isAccessible = true
            (errorField.get(this) as TextView).let {
                println("ERRORGRAVITY= $errorGravity")
                it.gravity = errorGravity
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.gravity = Gravity.END
                it.layoutParams = params
            }
        }catch (e: Exception){ e.printStackTrace() }
    }
}