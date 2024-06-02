package es.ulpgc.eite.da.hello_bye;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
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
import es.ulpgc.eite.da.hello_bye.hello.HelloActivity;
import es.ulpgc.eite.da.hello_bye.hello.HelloState;


//@RunWith(AndroidJUnit4.class)
@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloActivityTests {

    @Before
    public void setup() {
        AppMediator.resetInstance();
    }

    @Test
    public void test01StartActivity() {
        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);

        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);

            assertEquals(
                activity.getString(R.string.empty_text),
                helloMessageField.getText().toString()
            );

            HelloState state = AppMediator.getInstance().getHelloState();
            assertEquals(
                activity.getString(R.string.empty_text),
                state.helloMessage
            );
        });
    }

    @Test
    public void test02PressSayHelloButton() {
        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);


        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);
            Button sayHelloButton =
                activity.findViewById(R.id.sayHelloButton);

            sayHelloButton.performClick();

            assertEquals(
                activity.getString(R.string.hello_message),
                helloMessageField.getText().toString()
            );

            HelloState state = AppMediator.getInstance().getHelloState();
            assertEquals(activity.getString(R.string.hello_message), state.helloMessage);

        });
    }


    @Test
    public void test03PressGoByeButton() {

        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);

        scenario.onActivity(activity -> {

            activity.recreate();

            Button goByeButton = activity.findViewById(R.id.goByeButton);

            goByeButton.performClick();

            Intent expectedIntent = new Intent(activity, ByeActivity.class);
            Intent actualIntent = shadowOf(activity).getNextStartedActivity();

            assertEquals(
                expectedIntent.getComponent(),
                actualIntent.getComponent()
            );

            HelloToByeState state =
                AppMediator.getInstance().getHelloToByeState();
            assertEquals(activity.getString(R.string.empty_text), state.message);

        });

    }


    @Test
    public void test04PressSayHelloAndGoByeButtons() {

        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);

        scenario.onActivity(activity -> {

            activity.recreate();

            Button goByeButton =
                activity.findViewById(R.id.goByeButton);
            Button sayHelloButton =
                activity.findViewById(R.id.sayHelloButton);

            sayHelloButton.performClick();
            goByeButton.performClick();

            Intent expectedIntent = new Intent(activity, ByeActivity.class);
            Intent actualIntent = shadowOf(activity).getNextStartedActivity();

            assertEquals(
                expectedIntent.getComponent(),
                actualIntent.getComponent()
            );

            HelloToByeState state =
                AppMediator.getInstance().getHelloToByeState();
            assertEquals(activity.getString(R.string.hello_message), state.message);

        });

    }

    @Test
    public void test05ResumeActivityWithNullState() {

        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);

        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);

            assertEquals(
                activity.getString(R.string.empty_text),
                helloMessageField.getText().toString()
            );

            HelloState state = AppMediator.getInstance().getHelloState();
            assertEquals(activity.getString(R.string.empty_text), state.helloMessage);

        });

    }


    @Test
    public void test06ResumeActivityWithNotNullState() {

        String byeMessage = ApplicationProvider.getApplicationContext().getString(R.string.bye_message);

        ByeToHelloState savedState = new ByeToHelloState(byeMessage);
        AppMediator.getInstance().setByeToHelloState(savedState);

        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);

        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);

            assertEquals(
                activity.getString(R.string.bye_message),
                helloMessageField.getText().toString()
            );

            HelloState state = AppMediator.getInstance().getHelloState();
            assertEquals(activity.getString(R.string.bye_message), state.helloMessage);

        });

    }



    @Test
    public void test07PressSayHelloButtonAndResumeActivityWithNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloState state = new HelloState();
        state.helloMessage = helloMessage;
        AppMediator.getInstance().setHelloState(state);
        
        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);

        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);

            assertEquals(
                activity.getString(R.string.empty_text),
                helloMessageField.getText().toString()
            );

            HelloState updatedState =
                AppMediator.getInstance().getHelloState();
            assertEquals(activity.getString(R.string.empty_text), updatedState.helloMessage);

        });

    }



    @Test
    public void test08PressSayHelloButtonAndResumeActivityWithNotNullState() {

        String helloMessage = ApplicationProvider.getApplicationContext().getString(R.string.hello_message);

        HelloState state = new HelloState();
        state.helloMessage = helloMessage;
        AppMediator.getInstance().setHelloState(state);


        String byeMessage = ApplicationProvider.getApplicationContext().getString(R.string.bye_message);

        ByeToHelloState savedState = new ByeToHelloState(byeMessage);
        AppMediator.getInstance().setByeToHelloState(savedState);

        ActivityScenario<HelloActivity> scenario =
            ActivityScenario.launch(HelloActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);

        scenario.onActivity(activity -> {

            activity.recreate();

            TextView helloMessageField =
                activity.findViewById(R.id.helloMessage);

            assertEquals(
                activity.getString(R.string.bye_message),
                helloMessageField.getText().toString()
            );

            HelloState updatedState =
                AppMediator.getInstance().getHelloState();
            assertEquals(activity.getString(R.string.bye_message), updatedState.helloMessage);

        });

    }


}
