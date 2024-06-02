package es.ulpgc.eite.da.hello_bye.app;

public class HelloToByeState {

    public String message;

    public HelloToByeState(String message) {
        this.message = message;
    }

//  @Override
//  public HelloToByeState clone() {
//    try {
//      return (HelloToByeState) super.clone();
//    } catch (CloneNotSupportedException e) {
//      return null;
//    }
//  }

    //  @Override
//  public boolean equals(Object obj) {
//    if (this == obj) return true;
//    if (obj == null || getClass() != obj.getClass()) return false;
//    HelloToByeState that = (HelloToByeState) obj;
//    return Objects.equals(message, that.message);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(message);
//  }
//
//  @Override
//  public String toString() {
//    return "message: " + message;
//  }

}
