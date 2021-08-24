package com.example.myapplication.Utils

import android.util.Patterns
import androidx.core.text.isDigitsOnly

fun CharSequence?.isValidEmail()= !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
