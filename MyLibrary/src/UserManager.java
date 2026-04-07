import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;


class UserManager {
    private final String FILE_NAME = "users.txt";
    private List<User> users = new ArrayList<>();

    public boolean register(String username, String password) {
    // Сначала проверяем, есть ли уже такой пользователь
        for (User user : users) {
            if (user.getUsername().equals(username)) {
             return false; // уже есть
        }
    }
    // Если нет — создаём и добавляем
    User newUser = new User(username, password);
    users.add(newUser);
    return true;
    }

    @Override
    public String toString() {
        return "UserManager{" +
                "FILE_NAME='" + FILE_NAME + '\'' +
                ", users=" + users +
                '}';
    }

    public boolean login(String username, String password){
        for(User user : users) {
            if (user.getUsername().equals(username)) {
                return user.checkPassword(password);
            }
        }
            return false;
        }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


