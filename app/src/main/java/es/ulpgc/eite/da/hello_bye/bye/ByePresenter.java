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
    //state = mediator.getByeState();
  }


  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled()");

    state = new ByeState();
    //state = mediator.getByeState();
    mediator.setByeState(state);

    HelloToByeState savedState = mediator.getHelloToByeState();
    //HelloToByeState savedState = getDataFromHelloScreen();
    if(savedState != null){

      // set passed state
      state.byeMessage = savedState.message;
      //mediator.resetHelloToByeState();
    }

  }

  @Override
  public void onRecreateCalled() {
    Log.e(TAG, "onRecreateCalled()");

    state = mediator.getByeState();
  }

  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled()");

    /*HelloToByeState savedState = mediator.getHelloToByeState();
    //HelloToByeState savedState = getDataFromHelloScreen();
    if(savedState != null){

      // set passed state
      state.byeMessage = savedState.message;
      //mediator.resetHelloToByeState();
    }*/

    //state.message = state.byeMessage;
    view.get().displayByeData(state);
  }

  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled()");

    //mediator.setByeState(state);

    /*ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
    //passDataToHelloScreen(newState);
    mediator.setByeToHelloState(newState);*/

  }

  @Override
  public void onBackPressed() {
    //Log.e(TAG, "onBackPressed()");

    // reset passed state
    //state.byeMessage = state.message;
  }

  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled()");

    //mediator.resetByeState();
  }

  /*
  private void startByeMessageAsyncTask() {
    Log.e(TAG, "startByeMessageAsyncTask()");

    String message = model.getByeMessage();
    state.byeMessage = message;

    view.get().displayByeData(state);
  }

  @Override
  public void sayByeButtonClicked() {
    Log.e(TAG, "sayByeButtonClicked()");

    //state.message = state.byeMessage;
    state.byeMessage = "?";

    view.get().displayByeData(state);

    // call the model
    startByeMessageAsyncTask();
  }
  */

  @Override
  public void sayByeButtonClicked() {
    Log.e(TAG, "sayByeButtonClicked()");

    state.byeMessage = model.getByeMessage();

    view.get().displayByeData(state);
  }

  @Override
  public void goHelloButtonClicked() {

    ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
    //passDataToHelloScreen(newState);
    mediator.setByeToHelloState(newState);

    view.get().finishView();
  }


//  private HelloToByeState getDataFromHelloScreen() {
//    return mediator.getHelloToByeState();
//  }
//
//  private void passDataToHelloScreen(ByeToHelloState state) {
//    mediator.setByeToHelloState(state);
//  }

  @Override
  public void injectView(WeakReference<ByeContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ByeContract.Model model) {
    this.model = model;
  }
}
