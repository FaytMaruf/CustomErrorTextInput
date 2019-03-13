package com.irtikaz.customerrortextinput

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import java.lang.Integer.getInteger

class CustomErrorTextInput (context: Context, attrs: AttributeSet): TextInputLayout(context, attrs) {

    private var errorTextAllignment: Int = 0

    init {
        context.theme.obtainStyledAttributes( attrs, R.styleable.CustomErrorTextInput, 0, 0)
                .apply {
                    errorTextAllignment = getInteger(R.styleable.CustomErrorTextInput_errorTextAllignment, 0)
                }.recycle()
    }

    override fun setError(error: CharSequence?) {
        super.setError(error)

        if (!isErrorEnabled) return
        try{
            val errorField = TextInputLayout::class.java.getDeclaredField("mErrorView")
            errorField.isAccessible = true
            (errorField.get(this) as TextView).let {
                it.gravity = errorTextAllignment
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.gravity = Gravity.END
                it.layoutParams = params
            }
        }catch (e: Exception){ e.printStackTrace() }
    }
}