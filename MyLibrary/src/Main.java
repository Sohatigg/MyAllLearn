public class Main {
    public static void main(String[] args) {
        UserManager manager = new UserManager();

        // Тест регистрации
        System.out.println("Регистрация Анны: " +
                manager.register("Анна", "12345"));
        System.out.println("Регистрация Анны ещё раз: " +
                manager.register("Анна", "12345")); // должно быть false

        // Тест входа
        System.out.println("Вход Анна/12345: " +
                manager.login("Анна", "12345"));
        System.out.println("Вход Анна/54321: " +
                manager.login("Анна", "54321")); // должно быть false
    }
}