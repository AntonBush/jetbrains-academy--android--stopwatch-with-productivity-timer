val lambda: (Long, Long) -> Long = fun(a: Long, b: Long): Long {
    var mult = 1L
    for (i in a..b) {
        mult *= i
    }
    return mult
}

