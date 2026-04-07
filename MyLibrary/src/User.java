import java.io.Serializable;
import java.time.LocalDate;

class User implements Serializable {
    String username;
    String password;
    LocalDate registrationDate;

    // Конструктор — внутри класса!
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.registrationDate = LocalDate.now();
    }

    // Геттер — внутри класса!
    public String getUsername() {
        return username;
    }

    // Проверка пароля — внутри класса!
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}