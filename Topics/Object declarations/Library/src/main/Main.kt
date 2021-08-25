object Math {
    fun abs(number: Int): Int {
        return if (number < 0) -number else number
    }
    
    fun abs(number: Double): Double {
        return if (number < 0) -number else number
    }
}
