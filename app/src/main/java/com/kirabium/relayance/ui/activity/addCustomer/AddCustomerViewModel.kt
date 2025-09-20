package com.kirabium.relayance.ui.activity.addCustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    fun addCustomer(customer: Customer, callback: (List<Customer>?, String?) -> Unit) {
        // Vérification si l'email est valide
        if (!isValidEmail(customer.email)) {
            callback(null, "Please enter a valid email address.")
            return
        }

        viewModelScope.launch {
            try {
                // Ajout d'un client au repository
                val updatedCustomer = customerRepository.addCustomer(customer)
                callback(updatedCustomer, null)
            } catch (e: Exception) {
                callback(null, "An error occurred: ${e.message}")
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}