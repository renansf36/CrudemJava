import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args){
        while (true){
            System.out.println("\n=== CRUD Menu ===");
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuário");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Deletar Usuário");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> listUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
                    
            }
        }
    }

    private static void createUser(){
        System.out.print("Digite o nome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        User user = new User(idCounter++, name, email);
        users.add(user);
        System.out.println("Usuário criado com sucesso: " + user);
    }

    private static void listUser() {
        if (users.isEmpty()){
            System.out.println("Nenhum usuário encontrado.");
        }else{
            System.out.println("\n=== Lista de Usuários ===");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void updateUser() {
        System.out.println("Digite o ID do usuário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User user = findUserById(id);
        if (user == null){
            System.out.println("Usuário com ID" + id + " não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome (deixe em branco para manter o atual): ");
        String name = scanner.nextLine()
        if (!name.isEmpty()){
            user.setName(name);
        }

        System.out.println("Digite o novo email (deixe em branco para manter o atual): ");
        String email = scanner.nextLine()
        if (!email.isEmpty()){
            user.setEmail(email);
        }

        System.out.println("Usuário atualizado com sucesso: " + user);
    }

    private static void deleteUser(){
        System.out.print("Digite o ID do usuário a ser deletado:");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user == null) {
            System.out.println("Usuário com ID " + id + "não encontrado.");
            return;
        }

        users.remove(user);
        System.out.println("Usuário deletado com sucesso: " + user);
    }

    private static User findUserById(int id){
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
