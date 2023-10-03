package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "";
        String lastName = "";
        String fullName = firstName + " " + lastName;

        fullName = firstName.concat(" ").concat(lastName);
    }
}
