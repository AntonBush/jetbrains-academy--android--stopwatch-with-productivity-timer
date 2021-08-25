class Block(val color: String) {
    object BlockProperties {
        var length: Int = 0
        var width: Int = 0
        fun blocksInBox(boxLength: Int, boxWidth: Int): Int {
            return (boxLength / length) * (boxWidth / width)
        }
    }
}
