public class Main {

    public static void main(String[] args) throws MyExceptions {
        try {
            Operations.verifier(args);
            Operations.operations(args);
        } catch (MyExceptions ex) {
            System.out.println(ex.getMessage());
        }
    }

}

