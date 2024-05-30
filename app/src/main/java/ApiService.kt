import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)
data class ErrorResponse(val message: String)

data class RegisterRequest(val username: String, val password: String, val email: String)
interface ApiService {
    @POST("/api/user/login")
    fun login(@Body user: LoginRequest): Call<UserData>
    @POST("/api/user/register")
    fun register(@Body request: RegisterRequest): Call<UserData>

}