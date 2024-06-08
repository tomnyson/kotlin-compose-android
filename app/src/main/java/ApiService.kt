import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Date

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)
data class ErrorResponse(val message: String)

data class RegisterRequest(val username: String, val password: String, val email: String)

data class Category(
    val _id: String,
    val name: String,
)
data class Product(
    val _id: String,
    val name: String,
    val description: String,
    val images: ArrayList<String>,
    val price: Double,
    val createAt: Date,
    val category: Category?
)

data class CartItem(
    val productId: Product,
    val quantity: Int,
)

data class CartItemRequest(
    val productId: Product,
    val quantity: Int,
)
data class CartResponse(
    val userId: String,
    val items: List<CartItem>
)

data class CartRequest(
    val userId: String,
    val items: List<CartItemRequest>
)

interface ApiService {
    @POST("/api/user/login")
    fun login(@Body user: LoginRequest): Call<UserData>
    @POST("/api/user/register")
    fun register(@Body request: RegisterRequest): Call<UserData>

    @GET("/api/product")
    fun getProduct(): Call<List<Product>>

    @GET("/api/product/{id}")
    fun getProductDetail(@Path("id") id: String): Call<Product>

    @POST("/api/cart")
    fun saveCart(@Body request: CartRequest): Call<CartResponse>
    @GET("/api/cart/{userId}")
    fun getCart(@Path("id") id: String): Call<CartResponse>


}