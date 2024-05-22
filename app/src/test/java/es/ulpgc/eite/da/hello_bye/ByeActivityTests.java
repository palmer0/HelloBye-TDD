package es.ulpgc.eite.da.hello_bye;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.hello_bye.app.AppMediator;
import es.ulpgc.eite.da.hello_bye.app.ByeToHelloState;
import es.ulpgc.eite.da.hello_bye.app.HelloToByeState;
import es.ulpgc.eite.da.hello_bye.bye.ByeActivity;
import es.ulpgc.eite.da.hello_bye.bye.ByeActivity;
import es.ulpgc.eite.da.hello_bye.bye.ByeState;

//@RunWith(AndroidJUnit4.class)
@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ByeActivityTests {

    @Before
    public void setup() {
        AppMediator.resetInstance();
    }


    @Test
    public void test01StartActivityWithNullState() {

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);

            assertEquals(
                activity.getString(R.string.empty_text), // expected
                byeMessageField.getText().toString() // actual
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.empty_text), state.byeMessage);
        });
    }

    @Test
    public void test02StartActivityWithNotNullState() {
        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloToByeState savedState = new HelloToByeState(helloMessage);
        AppMediator.getInstance().setHelloToByeState(savedState);

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);

            assertEquals(
                activity.getString(R.string.hello_message),
                byeMessageField.getText().toString()
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.hello_message), state.byeMessage);
        });
    }

    @Test
    public void test03PressSayByeButtonWithNullState() {
        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);


        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);
            Button sayByeButton = activity.findViewById(R.id.sayByeButton);

            assertEquals(
                activity.getString(R.string.empty_text),
                byeMessageField.getText().toString()
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.empty_text), state.byeMessage);

            sayByeButton.performClick();

            assertEquals(
                activity.getString(R.string.bye_message),
                byeMessageField.getText().toString()
            );

            ByeState updatedState = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.bye_message), updatedState.byeMessage);

        });
    }


    @Test
    public void test04PressSayByeButtonWithNotNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloToByeState savedState = new HelloToByeState(helloMessage);
        AppMediator.getInstance().setHelloToByeState(savedState);

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);


        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);
            Button sayByeButton = activity.findViewById(R.id.sayByeButton);

            assertEquals(
                activity.getString(R.string.hello_message),
                byeMessageField.getText().toString()
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.hello_message), state.byeMessage);

            sayByeButton.performClick();

            assertEquals(
                activity.getString(R.string.bye_message),
                byeMessageField.getText().toString()
            );

            ByeState updatedState = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.bye_message), updatedState.byeMessage);

        });
    }



    @Test
    public void test05PressGoByeButtonWithNullState() {

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {
            Button goHelloButton = activity.findViewById(R.id.goHelloButton);

            goHelloButton.performClick();

            ByeToHelloState state = AppMediator.getInstance().getByeToHelloState();
            assertEquals(activity.getString(R.string.empty_text), state.message);

        });

    }

    @Test
    public void test06PressGoByeButtonWithNotNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloToByeState savedState = new HelloToByeState(helloMessage);
        AppMediator.getInstance().setHelloToByeState(savedState);

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {
            Button goHelloButton = activity.findViewById(R.id.goHelloButton);

            goHelloButton.performClick();

            ByeToHelloState state = AppMediator.getInstance().getByeToHelloState();
            assertEquals(activity.getString(R.string.hello_message), state.message);

        });

    }



    @Test
    public void test07PressBackButtonWithNullState() {

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {

            // Simulate back press
            activity.onBackPressed();

            ByeToHelloState state = AppMediator.getInstance().getByeToHelloState();
            assertNull(state);

        });

    }

    @Test
    public void test08PressBackButtonWithNotNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloToByeState savedState = new HelloToByeState(helloMessage);
        AppMediator.getInstance().setHelloToByeState(savedState);

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);

        scenario.onActivity(activity -> {

            // Simulate back press
            activity.onBackPressed();

            ByeToHelloState state = AppMediator.getInstance().getByeToHelloState();
            assertNull(state);

        });

    }


    @Test
    public void test09PressSayByeAndBackButtonsWithNullState() {
        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);


        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);
            Button sayByeButton = activity.findViewById(R.id.sayByeButton);

            assertEquals(
                activity.getString(R.string.empty_text),
                byeMessageField.getText().toString()
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.empty_text), state.byeMessage);

            sayByeButton.performClick();

            assertEquals(
                activity.getString(R.string.bye_message),
                byeMessageField.getText().toString()
            );

            ByeState updatedState = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.bye_message), updatedState.byeMessage);

            // Simulate back press
            activity.onBackPressed();

            ByeToHelloState newState = AppMediator.getInstance().getByeToHelloState();
            assertNull(newState);
        });
    }


    @Test
    public void test10PressSayByeAndBackButtonsWithNotNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloToByeState savedState = new HelloToByeState(helloMessage);
        AppMediator.getInstance().setHelloToByeState(savedState);

        ActivityScenario<ByeActivity> scenario = ActivityScenario.launch(ByeActivity.class);


        scenario.onActivity(activity -> {
            TextView byeMessageField = activity.findViewById(R.id.byeMessage);
            Button sayByeButton = activity.findViewById(R.id.sayByeButton);

            assertEquals(
                activity.getString(R.string.hello_message),
                byeMessageField.getText().toString()
            );

            ByeState state = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.hello_message), state.byeMessage);

            sayByeButton.performClick();

            assertEquals(
                activity.getString(R.string.bye_message),
                byeMessageField .getText().toString()
            );

            ByeState updatedState = AppMediator.getInstance().getByeState();
            assertEquals(activity.getString(R.string.bye_message), updatedState.byeMessage);

            // Simulate back press
            activity.onBackPressed();

            ByeToHelloState newState = AppMediator.getInstance().getByeToHelloState();
            assertNull(newState);
        });
    }


}
