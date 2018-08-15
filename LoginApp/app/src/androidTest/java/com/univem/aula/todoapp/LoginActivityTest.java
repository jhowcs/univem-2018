package com.univem.aula.todoapp;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.univem.aula.todoapp.view.LoginActivity;
import com.univem.aula.todoapp.view.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    IntentsTestRule<LoginActivity> rule
            = new IntentsTestRule<>(LoginActivity.class,
            true,
            false);

    @Test
    public void whenUserFillLoginAndPassword_thenShouldOpenMainActivity() {
        rule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.edtLogin))
                .perform(ViewActions.typeText("adm"));
        Espresso.onView(ViewMatchers.withId(R.id.edtPassword))
                .perform(ViewActions.typeText("123"));
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .perform(ViewActions.click());

        Matcher<Intent> intentMatcher =
                IntentMatchers.hasComponent(MainActivity.class.getName());
        Intents.intended(intentMatcher);
    }
}