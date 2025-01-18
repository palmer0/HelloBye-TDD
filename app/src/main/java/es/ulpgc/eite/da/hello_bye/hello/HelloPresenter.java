package es.ulpgc.eite.da.hello_bye.hello;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.hello_bye.app.AppMediator;
import es.ulpgc.eite.da.hello_bye.app.ByeToHelloState;
import es.ulpgc.eite.da.hello_bye.app.HelloToByeState;


public class HelloPresenter implements HelloContract.Presenter {

    public static String TAG = "HelloBye.HelloPresenter";

    private WeakReference<HelloContract.View> view;
    private HelloState state;
    private HelloContract.Model model;
    private AppMediator mediator;

    public HelloPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        state = new HelloState();
        mediator.setHelloState(state);
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        state = mediator.getHelloState();
    }


    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        ByeToHelloState savedState = mediator.getByeToHelloState();
        if (savedState != null) {

            // update state
            state.helloMessage = savedState.message;
        }

        // update the view
        view.get().displayHelloData(state);
    }


    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        mediator.setHelloState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");
    }

    @Override
    public void sayHelloButtonClicked() {
        Log.e(TAG, "sayHelloButtonClicked");

        // call the model
        state.helloMessage = model.getHelloMessage();

        // update the view
        view.get().displayHelloData(state);
    }

    @Override
    public void goByeButtonClicked() {
        Log.e(TAG, "goByeButtonClicked");

        HelloToByeState newState = new HelloToByeState(state.helloMessage);
        mediator.setHelloToByeState(newState);

        view.get().navigateToByeScreen();
    }

    @Override
    public void injectView(WeakReference<HelloContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(HelloContract.Model model) {
        this.model = model;
    }
}
