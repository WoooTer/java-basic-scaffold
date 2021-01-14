package wooter.db.balusc;

import java.text.SimpleDateFormat;
import java.util.List;

import wooter.db.balusc.dao.DAOFactory;
import wooter.db.balusc.dao.UserDAO;
import wooter.db.balusc.model.User;

public class DAOTest {

    public static void main(String[] args) throws Exception {
        // Obtain DAOFactory.
        DAOFactory javabase = DAOFactory.getInstance("wtdb.jdbc");
        System.out.println("DAOFactory successfully obtained: " + javabase);

        // Obtain UserDAO.
        UserDAO userDAO = javabase.getUserDAO();
        System.out.println("UserDAO successfully obtained: " + userDAO);

        // Create user.
        User user = new User();
        user.setEmail("foo@bar.com");
        user.setPassword("password");
        userDAO.create(user);
        System.out.println("User successfully created: " + user);

        // Create another user.
        User anotherUser = new User();
        anotherUser.setEmail("bar@foo.com");
        anotherUser.setPassword("anotherPassword");
        anotherUser.setFirstname("Bar");
        anotherUser.setLastname("Foo");
        anotherUser.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-03-26"));
        userDAO.create(anotherUser);
        System.out.println("Another user successfully created: " + anotherUser);

        // Update user.
        user.setFirstname("Foo");
        user.setLastname("Bar");
        userDAO.update(user);
        System.out.println("User successfully updated: " + user);

        // Update user.
        user.setFirstname("Foo");
        user.setLastname("Bar");
        userDAO.update(user);
        System.out.println("User successfully updated: " + user);

        // List all users.
        List<User> users = userDAO.list();
        System.out.println("List of users successfully queried: " + users);
        System.out.println("Thus, amount of users in database is: " + users.size());

        // Delete user.
        userDAO.delete(user);
        System.out.println("User successfully deleted: " + user);

        // Check if email exists.
        boolean exist = userDAO.existEmail("foo@bar.com");
        System.out.println("This email should not exist anymore, so this should print false: " + exist);

        // Change password.
        anotherUser.setPassword("newAnotherPassword");
        userDAO.changePassword(anotherUser);
        System.out.println("Another user's password successfully changed: " + anotherUser);

        // Get another user by email and password.
        User foundAnotherUser = userDAO.find("bar@foo.com", "newAnotherPassword");
        System.out.println("Another user successfully queried with new password: " + foundAnotherUser);

        // Delete another user.
        userDAO.delete(foundAnotherUser);
        System.out.println("Another user successfully deleted: " + foundAnotherUser);

        // List all users again.
        users = userDAO.list();
        System.out.println("List of users successfully queried: " + users);
        System.out.println("Thus, amount of users in database is: " + users.size());
    }

}
