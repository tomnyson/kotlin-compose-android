fun main() {
    """
        vong lap
    """.trimIndent()
    var i = 0
    while (i < 10) {
        if (i == 5) {
            break
        }
        println(i)
        i++
    }
    var sinhviens = arrayOf("Trần Hoàng Quân", "Nguyễn Xuân Ngọc", "Hồ Đức Hậu")
    for (sv in sinhviens) {
        println("sv $sv")
    }

    for (nums in 'a'..'x') {
        println(nums)
    }
    tinhTong()
    tich()
    phanTuLonNhat()
    LietKeChan()
    TimKiemPhanTu(-2)
    nhan2()
}

fun tinhTong() {
    var tong = 0
    val mang = arrayOf(1, 2, 4, 5, 6, 7, 8, 9, 9, 1, 2, 3)

    for (sv in mang) {
        tong += sv
    }
    println("tong cua mang nay la $tong")

}

//Tính tích * các phần tử trong mảng
fun tich() {
    var tich = 1
    val mang = arrayOf(1, 2, 3, 4, 5)
    for (i in mang) {
        tich *= i
    }
    println("tich cua mang$tich")
}

fun phanTuLonNhat() {
    val mang = arrayOf(1, 2, 3, 4, 5)
    var solonnhat = mang[0]

    for (i in mang) {
        if (i > solonnhat) {
            solonnhat = i
        }

    }
    println("so lon nhat cua mang la: $solonnhat")
}

fun KTChan(x: Int): Boolean {
    var sochan = 0
    if (x % 2 == 0) {
        return true
    }
    return false
}

fun LietKeChan() {
    val mang = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in mang)
        if (KTChan(i)) {
            println("so chan la $i")
        }


}

fun TimKiemPhanTu(n: Int) {

    val mang = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    for (x in mang) {
        if (n == x) {
            println("$n co trong mang")

            return
        }

    }
    println("$n khong co trong mang")

}

fun nhan2() {
    val mang = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in mang) {
        var x = i * 2
        println("mang moi la: $x")
    }
}



