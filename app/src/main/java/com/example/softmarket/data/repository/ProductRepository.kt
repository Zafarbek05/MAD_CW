import com.example.softmarket.data.Product
import com.example.softmarket.data.remote.ProductResponse
import com.example.softmarket.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    private val api = RetrofitInstance.api
    private val studentId = "00016417"

    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        api.getProducts(studentId).data.map { product: Product ->
            Product(
                title = product.title,
                provider = product.provider,
                category = product.category,
                targetPlatforms = product.targetPlatforms,
                billing = product.billing,
                price = product.price,
                logoUrl = product.logoUrl,
                size = product.size,
                about = product.about,
                quantity = product.quantity
            )
        }
    }

    suspend fun createProduct(product: Product): Product = withContext(Dispatchers.IO) {
        api.createProduct(studentId, product)
    }

    suspend fun updateProduct(id: Int, product: Product): Product = withContext(Dispatchers.IO) {
        api.updateProduct(id, studentId, product)
    }

    suspend fun deleteProduct(id: Int) = withContext(Dispatchers.IO) {
        api.deleteProduct(id, studentId)
    }
}