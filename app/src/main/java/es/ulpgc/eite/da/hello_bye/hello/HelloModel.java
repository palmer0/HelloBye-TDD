package es.ulpgc.eite.da.hello_bye.hello;

public class HelloModel implements HelloContract.Model {

    public static String TAG = "HelloBye.HelloModel";

    private String message;

    public HelloModel(String message) {
        this.message = message;
    }

    @Override
    public String getHelloMessage() {
        return message;
    }

}
