public class TeWeinigGeldException extends Exception {

    public String message;


    public TeWeinigGeldException(){

    }

    public TeWeinigGeldException(Exception e){

    }

    public TeWeinigGeldException(String message){
        this.message = message;
    }
}
