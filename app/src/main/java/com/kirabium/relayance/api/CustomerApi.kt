package com.kirabium.relayance.api

import com.kirabium.relayance.domain.model.Customer
import javax.inject.Inject

interface CustomerApi {
    suspend fun getCustomer(): List<Customer>
    // Retourne un nouveau client avec une nouvel ID
    suspend fun addCustomer(customer: Customer): Customer
}

class CustomerRepository @Inject constructor(
    private val customerApi: CustomerApi
) {
    suspend fun getCustomers(): List<Customer> {
        return customerApi.getCustomer()
    }

    suspend fun addCustomer(customer: Customer): List<Customer> {
        // retourne le client mis à jour avec ID
        val newCustomer = customerApi.addCustomer(customer)
        // Fetch
        return getCustomers()
    }
}