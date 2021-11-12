import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    /*
    nr este construit astfel:
    accesam semnul + sau - existent intre 'real' si 'imaginar'
    in stanga lui se gaseste partea reala iar in dreapta lui partea imaginara
     */

    public static Complex converter(String arg) {
        int real = 0;
        int imaginar = 0;
        for(int i = 1; i < arg.length(); i++)
            if (arg.charAt(i) == '+' || arg.charAt(i) == '-') {
                real = Integer.parseInt(arg.substring(0, i));
                imaginar = Integer.parseInt(arg.substring(i, arg.length()-1));
            }
        return new Complex(real, imaginar);
    }


    public static void verifier(String[] args) throws MyExceptions{
        if (args.length % 2 == 0 || args.length == 1)
            throw new MyExceptions("Invalid number of arguments");


/*
(+-)a(+-)bi
caracterul '-' sa apara de 0 ori sau 1 data urmat de
cifre de la 0 la 9 inclusiv sa apara de ori de cate ori
+/- de 0 ori sau o data
cifre de la 0 la 9 inclusiv sa apara de ori de cate ori
 */


        Pattern pattern = Pattern.compile("([-]?[0-9]*\\.?[0-9]?)([-|+]+[0-9]*\\.?[0-9]?)[i$]+");

        int i = 0;
        for(String arg : args) {
            if ((i % 2 == 0)) {
                Matcher matcher = pattern.matcher(arg);
                if (!matcher.find())
                    throw new MyExceptions("Number must have 'a+bi' form");
            }
            if ((i%2==1)){
                if(!(arg.charAt(0) == '+' || arg.charAt(0) == '-' || arg.charAt(0) == '*' || arg.charAt(0) == '/'))
                    throw new MyExceptions("Operators must be +, -, *, /");
            }

            i++;
        }

        int j;
        for(j=1; j< args.length; j+=2)
            if(!Objects.equals(args[j], args[1])){
                throw new MyExceptions("Operators are not the same");
            }

    }

    public static void operations(String[] args) throws MyExceptions {
        Complex[] complex_numbers = new Complex[(args.length / 2) + 1];
        int count = 0;
        for(int i = 0; i < args.length; i++)
            if(i % 2 == 0) {
                complex_numbers[count] = converter(args[i]);
                count++;
            }

        for(Complex number : complex_numbers)
            System.out.println(number);

        //addition
        if (args[1].charAt(0) == '+') {
            Complex result_add = complex_numbers[0];
            for (int i = 1; i < count; i++)
                result_add.addition(complex_numbers[i]);
            System.out.println("Addition: " + result_add);
        }

        //substraction
        if (args[1].charAt(0) == '-') {
            Complex result_scad = complex_numbers[0];
            for (int i = 1; i < count; i++)
                result_scad.subtraction(complex_numbers[i]);
            System.out.println("Subtraction: " + result_scad);
        }


        //multiplication
        if (args[1].charAt(0) == '*') {
            Complex result_inm = complex_numbers[0];
            for (int i = 1; i < count; i++)
                result_inm.multiplication(complex_numbers[i]);
            System.out.println("Multiplication: " + result_inm);
        }


        //division
        if (args[1].charAt(0) == '/') {
            Complex result_imp = complex_numbers[0];
            for (int i = 1; i < count; i++)
                result_imp.division(complex_numbers[i]);
            System.out.println("Division: " + result_imp);
        }


    }


}
