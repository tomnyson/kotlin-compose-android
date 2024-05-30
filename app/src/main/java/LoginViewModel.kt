import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_demo.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading
        ApiClient.apiService.login(LoginRequest(username, password))
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        _loginState.value = LoginState.Success(response.body()?.token ?: "")
                    } else {
                        _loginState.value = LoginState.Error("Login failed")
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    _loginState.value = LoginState.Error("Network error")
                }
            })
    }
}


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}