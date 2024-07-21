import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val productsList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dodaj przykładowe produkty
        addSampleProducts()

        // Utwórz i ustaw adapter dla RecyclerView
        val adapter = ProductAdapter(productsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Możliwość sortowania produktów według kategorii
        sortByCategoryButton.setOnClickListener {
            adapter.sortByCategory()
        }

        // Możliwość sortowania produktów według daty ważności
        sortByExpirationButton.setOnClickListener {
            adapter.sortByExpiration()
        }
    }

    private fun addSampleProducts() {
        productsList.add(Product("Mleko", "2024-05-01", Category.SPOZYWCZE, 1))
        productsList.add(Product("Pasta do zębów", "2024-08-15", Category.KOSMETYKI, 2))
        productsList.add(Product("Aspiryna", "2023-12-31", Category.LEKI))
        // Tutaj możesz dodać więcej przykładowych produktów
    }
}


