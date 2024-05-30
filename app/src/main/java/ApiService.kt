import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)
interface ApiService {
    @POST("/api/user/login")
    fun login(@Body user: LoginRequest): Call<UserData>
    @POST("/register")
    fun register(@Body user: UserData): Call<ResponseBody>

}