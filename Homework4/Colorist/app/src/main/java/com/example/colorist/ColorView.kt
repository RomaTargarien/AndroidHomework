package com.example.colorist

import android.graphics.Color
import java.io.Serializable
val COLORS_LIST_2 = listOf(ColorView(Color.BLUE,"BLUE"),
        ColorView(Color.RED,"RED"),
        ColorView(Color.WHITE,"WHITE"),
        ColorView(Color.YELLOW,"YELLOW"),
        ColorView(Color.LTGRAY,"LTGRAY"),
        ColorView(Color.TRANSPARENT,"TRANSPARENT"),
        ColorView(Color.MAGENTA,"MAGENTA"))

data class ColorView(val color: Int, val name: String) : Serializable