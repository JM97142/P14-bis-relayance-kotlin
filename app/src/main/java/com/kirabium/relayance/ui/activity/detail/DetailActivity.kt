package com.kirabium.relayance.ui.activity.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kirabium.relayance.R
import com.kirabium.relayance.extension.DateExt.Companion.toHumanDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CUSTOMER_ID = "customer_id"
    }

    private val detailActivityViewModel: DetailActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Récupère les vues du layout XML
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val dateTextView = findViewById<TextView>(R.id.dateTextView)
        val newRibbonTextView = findViewById<TextView>(R.id.newRibbonTextView)

        // Charge le client depuis l’intent
        val customerId = intent.getIntExtra(EXTRA_CUSTOMER_ID, -1)
        if (customerId != -1) {
            detailActivityViewModel.loadCustomer(customerId)
        }

        // Observe le flow de customer
        lifecycleScope.launchWhenStarted {
            detailActivityViewModel.customer.collect { customer ->
                if (customer != null) {
                    nameTextView.text = customer.name
                    emailTextView.text = customer.email
                    dateTextView.text = getString(
                        R.string.created_at,
                        customer.createdAt.toHumanDate()
                    )

                    // Affiche le ruban "Nouveau" si besoin
                    newRibbonTextView.visibility =
                        if (customer.isNewCustomer()) View.VISIBLE else View.GONE
                }
            }
        }
    }
}