package com.pro_1.prm_01_2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date
import java.util.Locale

//import com.pro_1.prm_01_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val productsList = mutableListOf<Product>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProduct)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productsList)
        recyclerView.adapter = productAdapter

        // Populate productsList
        productsList.add(Product("Mleko", "2024-05-01", Category.SPOZYWCZE, 1))
        productsList.add(Product("Krem", "2024-08-15", Category.KOSMETYKI, 2))
        productsList.add(Product("Ibuprofen", "2023-12-31", Category.LEKI))

        sortByExpirationDate()

        // Sort by Category button click listener
        findViewById<Button>(R.id.buttonSortCategory).setOnClickListener {
            sortByCategory()
        }

        // Sort by Expiration Date button click listener
        findViewById<Button>(R.id.buttonSortDate).setOnClickListener {
            sortByExpirationDate()
        }

        // Add element button click listener
        findViewById<Button>(R.id.buttonAddElement).setOnClickListener {
            showAddProductDialog()
        }

        // Add element button click listener
//        findViewById<Button>(R.id.buttonAddElement).setOnClickListener {
//            val randomCategory = Category.values().random()
//            val randomName = "Nowy produkt"  // TODO
//            val randomExpirationDate = "2025-01-01" // TODO
//            val randomQuantity = 5// TODO
//            productsList.add(Product(randomName, randomExpirationDate, randomCategory, randomQuantity))
//            productAdapter.notifyDataSetChanged()
//        }

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun sortByCategory() {
        productsList.sortBy { it.category }
        productAdapter.notifyDataSetChanged()
    }

    private fun sortByExpirationDate() {
        productsList.sortBy { it.expirationDate }
        productAdapter.notifyDataSetChanged()
    }

    @SuppressLint("CutPasteId", "NotifyDataSetChanged")
    private fun showAddProductDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_product, null)
        val spinnerCategory = dialogView.findViewById<Spinner>(R.id.spinnerCategory)
        val categoryNames = Category.values().map { it.name }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        val expirationDateButton = dialogView.findViewById<Button>(R.id.buttonExpirationDate)
        var selectedDate: Date? = null
        var displayDate = ""

        expirationDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, day)
                val currentTimeMillis = System.currentTimeMillis()
                val selectedTimeMillis = selectedCalendar.timeInMillis

                if (selectedTimeMillis < currentTimeMillis) {
                    Toast.makeText(this, "Nieprawidłowa data ważności", Toast.LENGTH_SHORT).show()
                } else {
                    val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, day)
                    selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(formattedDate)

                    displayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)

                    expirationDateButton.text = displayDate
                }
            }, year, month, dayOfMonth)

            datePickerDialog.show()
        }

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Dodaj Produkt")
            .setPositiveButton("Dodaj") { dialog, which ->
                val name = dialogView.findViewById<EditText>(R.id.editTextProductName)
                val categoryOption = dialogView.findViewById<Spinner>(R.id.spinnerCategory)
                val quantityOption = dialogView.findViewById<EditText>(R.id.editTextQuantity)

                val productName = name.text.toString().trim()
                val category = Category.valueOf(categoryOption.selectedItem.toString())
                val quantityText = quantityOption.text.toString().trim()

                // Walidacja danych
                if (productName.isEmpty() || selectedDate == null) {
                    Toast.makeText(this, "Wprowadź nazwę produktu i datę ważności", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val quantity = if (quantityText.isEmpty()) 0 else quantityText.toIntOrNull()
                if (quantity == null) {
                    Toast.makeText(this, "Ilość musi być liczbą", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val newProduct = Product(productName, displayDate, category, quantity)
                productsList.add(newProduct)
                productAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Anuluj") { dialog, which ->

            }
            .create()

        dialogBuilder.show()
    }

//    override fun onEditClicked(product: Product) {
//        showEditProductDialog(product)
//    }

    private fun showEditProductDialog(product: Product) {

    }

//    public fun showEditProductDialog(product: Product) {
//        val dialogView = layoutInflater.inflate(R.layout.add_product, null)
//        val spinnerCategory = dialogView.findViewById<Spinner>(R.id.spinnerCategory)
//        val categoryNames = Category.values().map { it.name }
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerCategory.adapter = adapter
//
//        val expirationDateButton = dialogView.findViewById<Button>(R.id.buttonExpirationDate)
//        var selectedDate: Date? = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(product.expirationDate)
//        expirationDateButton.text = product.expirationDate
//
//        expirationDateButton.setOnClickListener {
//            val calendar = Calendar.getInstance()
//            calendar.time = selectedDate ?: Date()
//
//            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
//                val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, day)
//                selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(formattedDate)
//                expirationDateButton.text = formattedDate
//            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
//
//            datePickerDialog.show()
//        }
//
//        val dialogBuilder = AlertDialog.Builder(this)
//            .setView(dialogView)
//            .setTitle("Edytuj Produkt")
//            .setPositiveButton("Zapisz") { dialog, which ->
//                val name = dialogView.findViewById<EditText>(R.id.editTextProductName)
//                val categoryOption = dialogView.findViewById<Spinner>(R.id.spinnerCategory)
//                val quantityOption = dialogView.findViewById<EditText>(R.id.editTextQuantity)
//
//                val productName = name.text.toString().trim()
//                val category = Category.valueOf(categoryOption.selectedItem.toString())
//                val quantityText = quantityOption.text.toString().trim()
//
//                // Walidacja danych
//                if (productName.isEmpty() || selectedDate == null) {
//                    Toast.makeText(this, "Wprowadź nazwę produktu i datę ważności", Toast.LENGTH_SHORT).show()
//                    return@setPositiveButton
//                }
//
//                val quantity = if (quantityText.isEmpty()) 0 else quantityText.toIntOrNull()
//                if (quantity == null) {
//                    Toast.makeText(this, "Ilość musi być liczbą", Toast.LENGTH_SHORT).show()
//                    return@setPositiveButton
//                }
//
//                product.name = productName
//                product.expirationDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate!!)
//                product.category = category
//                product.quantity = quantity
//
//                productAdapter.notifyDataSetChanged()
//            }
//            .setNegativeButton("Anuluj") { dialog, which ->
//
//            }
//            .create()
//
//        dialogBuilder.show()
//    }
}
