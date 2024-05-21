fun main() {
    """
        1: Kiểm tra độ tuổi Viết một hàm nhận vào tuổi và in ra "Trẻ em" nếu tuổi nhỏ hơn 12, "Thiếu niên" nếu tuổi từ 12 đến 17, "Người lớn" nếu tuổi từ 18 trở lên.
         2.Viết một hàm nhận vào điểm số (0-100) và in ra "A" cho 90-100, 
         "B" cho 80-89, "C" cho 70-79, "D" cho 60-69, và "F" cho 0-59. 
        3. Viết một hàm nhận vào số nguyên (1-7) và in ra ngày trong tuần tương ứng sử dụng 
        4. Viết một hàm nhận vào một chuỗi ("đỏ", "vàng", "xanh") và in ra hành động tương ứng ("Dừng lại", "Chờ", "Đi").
         5. Viết một hàm nhận vào một số nguyên và in ra "Chẵn" nếu số đó là số chẵn và "Lẻ" nếu số đó là số lẻ
        . 6.Viết một hàm nhận vào một số nguyên và in ra "Chẵn" nếu số đó là số chẵn và "Lẻ" nếu số đó là số lẻ.
         7. xây dựng máy tính cho phép nhập 2 sô a, b thực hiện phép tính + - * /
         8.Viết một hàm nhận vào một năm và kiểm tra xem năm đó có phải là năm nhuận không.
         9. Viết một hàm nhận vào ba cạnh của một tam giác và in ra loại tam giác đó (đều, cân, thường) 
        10. Viết một hàm nhận vào số tháng (1-12) và in ra mùa tương ứng (Xuân, Hạ, Thu, Đông).
        11. đảo ngược 1 chuỗi từ bàn phím

    """.trimIndent()

//    print("Nhập màu do, xanh, vang ")
    val nhap: String = readLine()!!
    val so = nhap.toIntOrNull() ?: 0
//
//    when(nhap){
//        "do"-> println("dung")
//        "xanh"-> println("di")
//        "vang"-> println("cho")
//        else -> println("k xac dinh mau $nhap")
//
//
//    }
//    var result = when(so){
//        1->"Monday"
//        2->"Tuesday"
//        3->"Wednesday"
//        4->"Thusday"
//        5->"Friday"
//        6->"Saturday"
//        7->"Sunday"
//        else->"Số nhập vào không hợp lệ"
//    }
//    println("Ngày tương ứng là $result")
//    if(so>=90&&so<=100){
//        println("A")
//    }else if(so>=80&&so<=89){
//        println("B")
//    }else if(so>=70&&so<=90){
//        println("C")
//    }else if(so>=60&&so<=69){
//        println("D")
//    }else if(so>=0&&so<=59){
//        println("F")
//    }else{
//        println("không có trong trong khoảng 0-100")
//    }


//kt_chan_le(so)

kt_tam_giac()
}
fun kt_tam_giac(){
    val a:String = readLine()!!
    val canh_a=a.toIntOrNull()?: 0
    val b:String = readLine()!!
    val canh_b=b.toIntOrNull()?: 0
    val c:String = readLine()!!
    val canh_c=c.toIntOrNull()?: 0
    println("nhap canh a =")
    print(canh_a)
    println("nhap canh b =")
    print(canh_b)
    println("nhap canh c =")
    print(canh_c)
    if (canh_a==canh_b || canh_a==canh_c){
        println("tam giac deu")
    }
    if (canh_a==canh_b || canh_a!=canh_c){
        println("tam giac can")
    }
    if (canh_a!=canh_b || canh_a!=canh_c || canh_b!=canh_c){
        println("tam giac bt")
    }
}
//fun kt_chan_le(so: Int){
//    println("nhap so vao")
//
//    if (so%2==0){
//        println("la so chan")
//    }
//    else{
//        println("la so le")
//    }
//
//}