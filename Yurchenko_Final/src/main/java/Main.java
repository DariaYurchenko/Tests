import model.entity.User;
import model.entity.entityenum.UserType;
import model.service.impl.UserService;
import uitility.encryption.EncryptorBuilder;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        //List<User> users = userService.findAll();
        //System.out.println(users);

       /* for(int i = 0; i < 30; i++) {
            EncryptorBuilder builder = new EncryptorBuilder("1234567");
            User user = new User.Builder()
                    .setName("Alex")
                    .setLastName("Alexeev")
                    .setLogin( i + "alex@gmail.com")
                    .setHash(builder.getHash())
                    .setSalt(builder.getSalt())
                    .setUserType(UserType.STUDENT)
                    //.setRank(345.54)
                    .build();

            userService.registerUser(user);
        }*/
        //System.out.println(userService.findUserById(11L));
        //userService.deleteUserById(11L);
        System.out.println(userService.findAll());
        //System.out.println(userService.findUsersByParameter("login", "Dsdchenkod@gmail.com"));
//        userService.updateUserById("name", "Yasha", 12L);
//        System.out.println(userService.findAll());
       // userService.deleteAllUsers();
        //System.out.println(userService.findAll());
        //userService.setRank(14L, 356, 345);
        //userService.changeUsersPassword(builder.getHash(), builder.getSalt(), "Dsdchenkod@gmail.com");
        //System.out.println(userService.findAll());

        //userService.setRank(14L, 154, 200);
        //System.out.println(userService.findAll());


    }
}
