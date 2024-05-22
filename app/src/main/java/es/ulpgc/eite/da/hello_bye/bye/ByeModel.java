package es.ulpgc.eite.da.hello_bye.bye;

public class ByeModel implements ByeContract.Model {

  public static String TAG = "HelloBye.ByeModel";

  private String message;

  public ByeModel(String message) {
    this.message = message;
  }


  @Override
  public String getByeMessage() {
    return message;
  }

}
