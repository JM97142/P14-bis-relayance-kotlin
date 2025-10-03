package com.kirabium.relayance.cucumber.steps

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.activity.main.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class ViewCustomerDetailSteps {

    @Given("I start the application")
    fun iLaunchTheApplication() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Given("I navigate on the customer list")
    fun iAmOnTheCustomerList() {
        onView(withId(R.id.customerRecyclerView))
            .check(matches(isDisplayed()))
    }

    @When("I click on the customer {string}")
    fun iClickOnTheCustomer(name: String) {
        // Clique sur le texte correspondant dans la liste
        onView(withText(name)).perform(click())
    }

    @Then("I should see the detail screen for {string} with email {string}")
    fun iShouldSeeTheDetailScreen(name: String, email: String) {
        onView(withId(R.id.nameTextView))
            .check(matches(withText(name)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.emailTextView))
            .check(matches(withText(email)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.dateTextView))
            .check(matches(isDisplayed()))
    }
}