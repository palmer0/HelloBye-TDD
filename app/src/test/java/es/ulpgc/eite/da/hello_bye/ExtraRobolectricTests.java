package es.ulpgc.eite.da.hello_bye;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.hello_bye.app.AppMediator;
import es.ulpgc.eite.da.hello_bye.bye.ByeActivity;
import es.ulpgc.eite.da.hello_bye.hello.HelloActivity;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExtraRobolectricTests {

    private ActivityController<HelloActivity> helloController;
    private HelloActivity helloActivity;

    private ActivityController<ByeActivity> byeController;
    private ByeActivity byeActivity;

    @Before
    public void setUp() {
        AppMediator.resetInstance();
        helloController = launchHello();
        helloActivity = helloController.get();
    }

    // ========================= TESTS =========================

    @Test
    public void test01MostrarHelloEnHello() {
        assertHelloMessage(R.string.empty_text);

        click(helloActivity, R.id.sayHelloButton);
        recreateHello();

        assertHelloMessage(R.string.hello_message);
    }

    @Test
    public void test02IrAByeSinMostrar() {
        assertHelloMessage(R.string.empty_text);

        click(helloActivity, R.id.goByeButton);
        launchByeFromHello();
        recreateBye();

        assertByeMessage(R.string.empty_text);

        byeActivity.onBackPressed();
        destroyBye();
        resumeHello();

        assertHelloMessage(R.string.empty_text);
    }

    @Test
    public void test03IrAByeMostrandoHello() {
        click(helloActivity, R.id.sayHelloButton);
        recreateHello();

        click(helloActivity, R.id.goByeButton);
        launchByeFromHello();
        recreateBye();

        assertByeMessage(R.string.hello_message);

        byeActivity.onBackPressed();
        destroyBye();
        resumeHello();

        assertHelloMessage(R.string.hello_message);
    }

    // ========================= HELPERS =========================

    private ActivityController<HelloActivity> launchHello() {
        return Robolectric.buildActivity(HelloActivity.class)
                .create()
                .start()
                .resume()
                .visible();
    }

    private void recreateHello() {
        Bundle bundle = new Bundle();
        helloController.pause().saveInstanceState(bundle).stop().destroy();

        helloController = Robolectric.buildActivity(HelloActivity.class)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible();

        helloActivity = helloController.get();
    }

    private void launchByeFromHello() {
        Intent nextIntent = shadowOf(helloActivity).getNextStartedActivity();
        assertNotNull(nextIntent);

        helloController.pause().stop();

        byeController = Robolectric.buildActivity(ByeActivity.class, nextIntent)
                .create()
                .start()
                .resume()
                .visible();

        byeActivity = byeController.get();
    }

    private void recreateBye() {
        byeController = byeController.recreate();
        byeActivity = byeController.get();
    }

    private void destroyBye() {
        byeController.pause().stop().destroy();
    }

    private void resumeHello() {
        helloController.restart().start().resume().visible();
        helloActivity = helloController.get();
    }

    private void click(@NonNull HelloActivity activity, int buttonId) {
        Button button = activity.findViewById(buttonId);
        button.performClick();
    }

    private void click(@NonNull ByeActivity activity, int buttonId) {
        Button button = activity.findViewById(buttonId);
        button.performClick();
    }

    private void assertHelloMessage(int stringRes) {
        TextView textView = helloActivity.findViewById(R.id.helloMessage);
        assertEquals(helloActivity.getString(stringRes), textView.getText().toString());
    }

    private void assertByeMessage(int stringRes) {
        TextView textView = byeActivity.findViewById(R.id.byeMessage);
        assertEquals(byeActivity.getString(stringRes), textView.getText().toString());
    }
}