package design.design;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator {
    public static User[] generateUsers(int count) {
        User[] users = new User[count];
        Random random = new Random();

        for (int i = 0; i < count; i++)
            users[i] = new User(
                    generate(7),
                    generate(11),
                    random.nextInt(70) + 18,
                    Sex.values()[random.nextInt(Sex.values().length)],
                    generateDescription(random.nextInt(5) + 1));

        return users;
    }

    private static String generate(int length) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String generateDescription(int lines) {
        StringBuilder description = new StringBuilder();
        Random random = new Random();
        int words;

        for (int i = 0; i < lines; i++) {
            words = random.nextInt(5) + 5;
            for (int j = 0; j < words; j++)
                description.append(generate(random.nextInt(12)));

            description.append("\n");
        }

        return description.toString();
    }
}
