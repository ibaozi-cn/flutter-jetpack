package com.pape.camera.option


/**
 * 网格值可用于在相机预览上绘制网格线
 *
 * @see CameraView.setGrid
 */
enum class Grid(private val value: Int) : Control {


    /**
     * No grid is drawn.
     */
    OFF(0),

    /**
     * Draws a regular, 3x3 grid.
     */
    DRAW_3X3(1),

    /**
     * Draws a regular, 4x4 grid.
     */
    DRAW_4X4(2),

    /**
     * Draws a grid respecting the 'phi' constant proportions,
     * often referred as to the golden ratio.
     */
    DRAW_PHI(3);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = OFF

        fun fromValue(value: Int): Grid? {
            val list = Grid.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}