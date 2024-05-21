fun main() {
    print("")
    """
        số học: + - * /
        phép gán: +=, -=, *=, /=, %=
        so sánh: ==, !=, >, <, >=, <=
        logic: &&, ||, !
    """.trimIndent()
    // so hoc
//    var x=5;
//    var y=3;
//    var z=x+y;
//    println()
//    print (x+y)
//    println()
//    print (x*y)
//    println()
//    print (y-x)
    //phép gán

//    var x=5
//    x += 3
//    println(x)
//
//    x=5
//    x -=3
//    println(x)
//
//    x=5
//    x *= 3
//    println(x)
//
//    x=5
//    x /= 3
//    println(x)
//
//    x=5
//    x %= 3
//    println(x)

//phep so sanh
    var a = 1
    var b = 2
    var bang = a==b
    var khacBang = a!=b
    var lonHon = a>b
    var beHon = a<b
    var lonHonBang = a>=b
    var behonBang = a<=b

    println(bang)
    println(khacBang)
    println(lonHon)
    println(beHon)
    println(lonHonBang)
    println(behonBang)

    var y = 1

    println("dieu kien")
    var dieukienva = y>1 && y ==a
    println(dieukienva)
    var dieukienchinhxac = y>1 || y ==a
    println(dieukienchinhxac)
    var dieukienkhac = y != 1
    println(dieukienkhac)
    """
        làm việc với string
    """.trimIndent()
    println("string---------")
    var ho = "Lê"
    var ten = "Hồng Sơn"
    println(ho + " "+ ten)
    println("xin chào: $ten")
    """
       1. Nhập tên đầy đủ của ban
       \val stringInput = readLine()!!
        tìm xem Lê có nằm trong Họ tên đầy đủ của bạn không
       2. đếm ký tự của tên nếu >12 ký tự => quá dài, bt
       3. convert tên thành in hoa
       4. in ra xin chào kèm theo họ tên đầy đủ
    """.trimIndent()
    println("nhập tên của bạn")
    val data = readLine()!!
    val name = "Le"
    if (data.indexOf("Le")>=0)
        println("le co trong ten cua ban")
    else (
            println("le khong co trong ten cua ban")
    )

        if(data.length>12){
            println("qua dai")
        }
    else{
        println("binh thuong")
    }
    println (data.uppercase())
    print("xin chaof $data")
}