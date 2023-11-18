package javaTester;

public class Topic_09_Array {
    int[] studentAge = {15, 10 , 20, 22};

    static String[] studentName = {"Nguyễn Văn A","Nguễn Văn B"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Lê Lợi";
        studentAddress[1] = "Lê Lai";
        studentAddress[2] = "Lê Duẩn";
        studentAddress[3] = "Lê Đại Hành";
        studentAddress[4] = "Lê Thánh Tôn";
        System.out.println(studentName[0]);
        Topic_09_Array topic = new Topic_09_Array() ;
        System.out.println(topic.studentAge[0]);

        for (int i = 0; i < studentAddress.length; i++){
            System.out.println(studentAddress[i]);
        }
    }


}
