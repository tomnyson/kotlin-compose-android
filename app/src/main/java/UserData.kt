data class UserDataInfo(val username: String,
                        val password: String,
                        val email: String, val _id: String)
data class UserData(val message: String,
                    val data: UserDataInfo,
                    val token: String?
    ) {
    override fun toString(): String {
        return "UserData(message='$message', data=$data, token=$token)"
    }
}