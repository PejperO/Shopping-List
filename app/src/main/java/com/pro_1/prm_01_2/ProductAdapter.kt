package com.pro_1.prm_01_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_PRODUCT = 0
    private val VIEW_TYPE_SUMMARY = 1

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewProductName)
        val expirationDateTextView: TextView = itemView.findViewById(R.id.textViewExpirationDate)
        val categoryTextView: TextView = itemView.findViewById(R.id.textViewCategory)
        val editButton: Button = itemView.findViewById(R.id.buttonEditElement)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDeleteElement)

//        init {
//            editButton.setOnClickListener {
//                val product = products[position]
//                showEditProductDialog(product)
//            }
//        }
    }

    inner class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val summaryTextView: TextView = itemView.findViewById(R.id.textViewSummary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //val buttonEditElement = itemView.findViewById<Button>(R.id.buttonEditElement)

        return if (viewType == VIEW_TYPE_PRODUCT) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
            ProductViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_summary, parent, false)
            SummaryViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_PRODUCT) {
            val product = products[position]
            val productHolder = holder as ProductViewHolder
            productHolder.nameTextView.text = product.name
            productHolder.expirationDateTextView.text = product.expirationDate
            productHolder.categoryTextView.text = product.category.name
//            if (product.quantity != 0) {
//                productHolder.quantityTextView.text = product.quantity.toString()
//            } else {
//                productHolder.quantityTextView.visibility = View.GONE
//            }
        } else {
            val summaryHolder = holder as SummaryViewHolder
            val totalQuantity = products.filter { it.quantity != 0 }.sumBy { it.quantity }
            summaryHolder.summaryTextView.text = "SUMA: $totalQuantity"
        }

//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            if (context is MainActivity) {
//                //context.showEditProductDialog() ?
//            }
//        }

//        holder.buttonEditElement.setOnClickListener {
//            val product = products[position]
//            showEditProductDialog(product)
//        }

//        holder.deleteButton.setOnClickListener {
//            // Wywołaj metodę usuwania produktu, przekazując produkt do usunięcia
//            // Na przykład:
//            // deleteProduct(product)
//        }
    }

    override fun getItemCount(): Int {
        return products.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < products.size) {
            VIEW_TYPE_PRODUCT
        } else {
            VIEW_TYPE_SUMMARY
        }
    }

//    private fun showEditProductDialog(product: Product) {
//        val dialogView = layoutInflater.inflate(R.layout.edit_product, null)
//
//        val nameEditText = dialogView.findViewById<EditText>(R.id.editTextProductName)
//        val expirationDateButton = dialogView.findViewById<Button>(R.id.buttonExpirationDate)
//        val spinnerCategory = dialogView.findViewById<Spinner>(R.id.spinnerCategory)
//        val quantityEditText = dialogView.findViewById<EditText>(R.id.editTextQuantity)
//
//        nameEditText.setText(product.name)
//        expirationDateButton.text = product.expirationDate
//        spinnerCategory.setSelection(product.category.ordinal)
//        quantityEditText.setText(product.quantity.toString())
//
//        val dialogBuilder = AlertDialog.Builder(this)
//            .setView(dialogView)
//            .setTitle("Edytuj Produkt")
//            .setPositiveButton("Zapisz") { dialog, which ->
//                //TODO
//            }
//            .setNegativeButton("Anuluj") { dialog, which ->
//
//            }
//            .create()
//
//        dialogBuilder.show()
//    }

}


