enum class Rainbow (val color:String, val nr:Int){
    RED("red", 1),
    ORANGE("orange", 2),
    YELLOW("yellow",3),
    GREEN("green", 4),
    BLUE("blue", 5),
    INDIGO("indigo", 6),
    VIOLET("violet", 7)
}
fun main() {
    val color = readLine()!!
    for(c in Rainbow.values()) {
        if (c.color == color) {
            println(true)
            return
        }
    }
    println(false)
}