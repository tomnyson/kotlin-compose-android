//class SinhVien {
//    var mssv = ""
//    var tensv = ""
//    var dtb = 0
//}
interface HDSinhVien {
    fun hoc(): String
    fun thi(): String
}

open class SinhVien(var mssv: String, var tensv: String, var dtb: Int) {
    open fun xuatThongTin() {
        println("mssv: $mssv")
        println("ten: $tensv")
        println("dtb: $dtb")
    }

    // xep loai
    fun xepLoai(dtb: Int) {
        if (dtb >= 9 && dtb <= 10) {
            println("Gioi")
        } else if (dtb < 9 && dtb >= 7) {
            println("Kha")
        } else println("TB")
    }
}

class SinhVienCNTT(mssv: String, tensv: String, dtb: Int, var diemNhapMonLapTrinh: Int) :
    SinhVien(mssv, tensv, dtb), HDSinhVien {
    override fun xuatThongTin() {
        super.xuatThongTin()
        println("Diem nhap mon lap trinh: $diemNhapMonLapTrinh")
        println("Sinh Vien hoc: ${hoc()}")
        println("Sinh Vien thi: ${thi()}")
    }
    override fun hoc() = "hoc CNTT"
    override fun thi() = "Thi Nhap Mon LT"

}

// do hoa
class SvDoHoa(mssv: String, tensv: String, dtb: Int, var diemPhoToShop: Double) :
     SinhVien(mssv, tensv, dtb),HDSinhVien {
    override fun xuatThongTin() {

            super.xuatThongTin()
            println("Diem nhap mon lap trinh: $diemPhoToShop")
            println("Sinh Vien hoc: ${hoc()}")
            println("Sinh Vien thi: ${thi()}")
    }
    override fun hoc() = "hoc thiet ke do "
    override fun thi() = "thi do hoa"

}

fun main() {
//var sv1 = SinhVien()
//    sv1.mssv = "pk1234"
//    sv1.tensv = "nguyen van A"
//    sv1.dtb = 8
//}

    var sv1 = SinhVien("pk1234", "nguyen van A", 8)
    var sv2 = SinhVien("pk1236", "nguyen van B", 6)
    sv1.xuatThongTin()
    sv2.xuatThongTin()
    sv1.xepLoai(sv1.dtb)
    sv2.xepLoai(sv2.dtb)

    var svCNTT = SinhVienCNTT("pk1234", "nguyen van A", 8, 9)
    svCNTT.xuatThongTin()

    var svDH = SvDoHoa("123", "nguyen van A", 8, 8.6)
    svDH.xuatThongTin()
}
