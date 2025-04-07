import com.example.softmarket.data.Product
import com.example.softmarket.data.remote.ProductResponse
import retrofit2.http.*

interface ProductApi {
    @GET("records/all")
    suspend fun getProducts(
        @Query("student_id") studentId: String
    ): ProductResponse

    @POST("records")
    suspend fun createProduct(
        @Query("student_id") studentId: String,
        @Body product: Product
    ): Product

    @PUT("records/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Query("student_id") studentId: String,
        @Body product: Product
    ): Product

    @DELETE("records/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int, @Query("student_id") studentId: String)
}