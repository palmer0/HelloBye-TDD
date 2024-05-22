package es.ulpgc.eite.da.hello_bye.app;


import es.ulpgc.eite.da.hello_bye.bye.ByeState;
import es.ulpgc.eite.da.hello_bye.hello.HelloState;

public class AppMediator {

//  private HelloState helloState = new HelloState();
//  private ByeState byeState = new ByeState();

  private HelloState helloState;
  private ByeState byeState;

  private HelloToByeState helloToByeState;
  private ByeToHelloState byeToHelloState;


  private static AppMediator INSTANCE;

  private AppMediator() {
    //helloState = new HelloState();
    //byeState = new ByeState();
  }

  public static void resetInstance() {
    INSTANCE = null;
  }


  public static AppMediator getInstance() {
    if(INSTANCE == null){
      INSTANCE = new AppMediator();
    }

    return INSTANCE;
  }

  public void setHelloState(HelloState state) {
    this.helloState = state;
  }

  public void setByeState(ByeState state) {
    this.byeState = state;
  }

  public ByeState getByeState() {
    return byeState;
  }

  public HelloState getHelloState() {
    return helloState;
  }

  public HelloToByeState getHelloToByeState() {
    HelloToByeState state = helloToByeState;
    //HelloToByeState state = helloToByeState.clone();
    helloToByeState = null;
    return state;
  }

  public void setHelloToByeState(HelloToByeState state) {
    helloToByeState = state;
    //helloToByeState = state.clone();
  }

  public ByeToHelloState getByeToHelloState() {
    ByeToHelloState state = byeToHelloState;
    //ByeToHelloState state = byeToHelloState.clone();
    byeToHelloState = null;
    return state;
  }

  public void setByeToHelloState(ByeToHelloState state) {
    byeToHelloState = state;
    //byeToHelloState = state.clone();
  }

//  public void resetByeState() {
//    byeState  = null;
//  }
//
//  public void resetHelloToByeState() {
//    helloToByeState  = null;
//  }
//
//  public void resetByeToHelloState() {
//    byeToHelloState  = null;
//  }

}
