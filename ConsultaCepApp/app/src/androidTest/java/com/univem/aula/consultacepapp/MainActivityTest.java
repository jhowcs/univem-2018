package com.univem.aula.consultacepapp;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import br.com.concretesolutions.requestmatcher.model.HttpMethod;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public RequestMatcherRule server =
            new InstrumentedTestRequestMatcherRule();

    @Rule
    public ActivityTestRule<MainActivity> rule
            = new ActivityTestRule(MainActivity.class,
            true, false);

    final String succesResult = "logradouro: " + "Rua Álvaro Luís Roberto de Assumpção";

    @Before
    public void setUp() {
        final String url = server.url("/").toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CepRepository.swapRetrofitForTest(retrofit);
    }

    @Test
    public void whenUserTypeCepAndClickOnSearch_thenShouldFetchDataFromAPI() throws InterruptedException {
        // arrange
        rule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.edtCep))
                .perform(ViewActions.typeText("04618020"));

        server.addFixture(200, "cep_response_200.json")
                .ifRequestMatches().methodIs(HttpMethod.GET)
                .pathIs("/ws/04618020/json");

        // act
        Espresso.onView(ViewMatchers.withId(R.id.btnBusca))
                .perform(ViewActions.click());

        // assert
        Espresso.onView(ViewMatchers.withId(R.id.txtResult))
                .check(ViewAssertions.matches(ViewMatchers.withText(succesResult)));
    }

    @Test
    public void whenUserTypeCepAndClickOnSearch_thenShouldReturnGenericError() {
        // arrange
        rule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.edtCep))
                .perform(ViewActions.typeText("04618020"));

        server.addFixture(500, "cep_response_500.json")
                .ifRequestMatches().methodIs(HttpMethod.GET)
                .pathIs("/ws/04618020/json");

        // act
        Espresso.onView(ViewMatchers.withId(R.id.btnBusca))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.txtResult))
                .check(ViewAssertions.matches(ViewMatchers.withText("generic error")));
    }
}