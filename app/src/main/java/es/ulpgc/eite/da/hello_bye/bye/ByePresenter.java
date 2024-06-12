package es.ulpgc.eite.da.hello_bye.bye;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.hello_bye.app.AppMediator;
import es.ulpgc.eite.da.hello_bye.app.ByeToHelloState;
import es.ulpgc.eite.da.hello_bye.app.HelloToByeState;


public class ByePresenter implements ByeContract.Presenter {

    public static String TAG = "HelloBye.ByePresenter";

    private WeakReference<ByeContract.View> view;
    private ByeState state;
    private ByeContract.Model model;
    private AppMediator mediator;


    public ByePresenter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        state = new ByeState();

        HelloToByeState savedState = mediator.getHelloToByeState();
        if (savedState != null) {

            // use saved state to update current state
            state.byeMessage = savedState.message;
        }

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        state = mediator.getByeState();
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        view.get().displayByeData(state);
    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        mediator.setByeState(state);
    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed");

    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");

    }


    @Override
    public void sayByeButtonClicked() {
        Log.e(TAG, "sayByeButtonClicked");

        state.byeMessage = model.getByeMessage();

        view.get().displayByeData(state);
    }

    @Override
    public void goHelloButtonClicked() {
        Log.e(TAG, "goHelloButtonClicked");

        ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
        mediator.setByeToHelloState(newState);

        view.get().finishView();
    }


    @Override
    public void injectView(WeakReference<ByeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ByeContract.Model model) {
        this.model = model;
    }
}
