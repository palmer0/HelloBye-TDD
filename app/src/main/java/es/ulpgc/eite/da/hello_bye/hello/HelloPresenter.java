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
    //state = mediator.getHelloState();
  }

  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled()");

    //state = mediator.getHelloState();
    state = new HelloState();
    mediator.setHelloState(state);
  }

  @Override
  public void onRecreateCalled() {
    Log.e(TAG, "onRecreateCalled()");
    state = mediator.getHelloState();
  }


  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled()");

    //ByeToHelloState savedState = getDataFromByeScreen();
    ByeToHelloState savedState = mediator.getByeToHelloState();
    if(savedState != null){

      // update state
      state.helloMessage = savedState.message;
      //mediator.resetByeToHelloState();
    }

    // update the view
    view.get().displayHelloData(state);
  }



  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled()");

    //mediator.setHelloState(state);

    /*HelloToByeState newState = new HelloToByeState(state.helloMessage);
    //passDataToByeScreen(newState);
    mediator.setHelloToByeState(newState);*/
  }

  /*
  private void startHelloMessageAsyncTask() {
    //Log.e(TAG, "startHelloMessageAsyncTask()");

    // este codigo puede ir tambien en goByeButtonClicked()
    String message=model.getHelloMessage();
    state.helloMessage = message;

    view.get().displayHelloData(state);
  }

  @Override
  public void sayHelloButtonClicked() {
    //Log.e(TAG, "sayHelloButtonClicked()");

    state.helloMessage = "?";

    view.get().displayHelloData(state);

    // call the model
    startHelloMessageAsyncTask();

  }
  */

  @Override
  public void sayHelloButtonClicked() {
    //Log.e(TAG, "sayHelloButtonClicked()");

    // call the model
    state.helloMessage = model.getHelloMessage();

    // update the view
    view.get().displayHelloData(state);
  }

  @Override
  public void goByeButtonClicked() {
    //Log.e(TAG, "goByeButtonClicked()");

    HelloToByeState newState = new HelloToByeState(state.helloMessage);
    //passDataToByeScreen(newState);
    mediator.setHelloToByeState(newState);

    view.get().navigateToByeScreen();
  }

//  private ByeToHelloState getDataFromByeScreen() {
//    return mediator.getByeToHelloState();
//  }
//
//  private void passDataToByeScreen(HelloToByeState state) {
//    mediator.setHelloToByeState(state);
//  }


  @Override
  public void injectView(WeakReference<HelloContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HelloContract.Model model) {
    this.model = model;
  }
}
