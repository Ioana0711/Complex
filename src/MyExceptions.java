public class MyExceptions extends Throwable{
    public String message;
    public MyExceptions(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}