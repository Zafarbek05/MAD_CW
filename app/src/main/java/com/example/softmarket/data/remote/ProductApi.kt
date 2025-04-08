import com.example.softmarket.data.Product
import com.example.softmarket.data.remote.ProductRequest
import com.example.softmarket.data.remote.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("records/all")
    suspend fun getProducts(
        @Query("student_id") studentId: String
    ): Response

    @POST("records")
    suspend fun createProduct(
        @Query("student_id") studentId: String,
        @Body product: ProductRequest
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