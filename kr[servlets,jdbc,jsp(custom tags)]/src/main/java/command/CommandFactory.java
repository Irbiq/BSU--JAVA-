package command;

public class CommandFactory {


     public static Command newInstance(String command){

        switch (command){

            case "destroy":
                return new DestroyCommand();
            case "add":
                return new AddCommand();
            case "fromError":
                return new FromErrorCommand();
            case "students":
                return new StudentCommand();
            default:
                return null;
        }
    }

}
