package max.tangome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class UserStats {

    private final Map<String, AtomicLong> hitsPerUser = new ConcurrentHashMap<>();

    public void onUserCall(String userId) {

        if (!hitsPerUser.containsKey(userId)) {
            hitsPerUser.put(userId, new AtomicLong(1L));

            System.out.println(Thread.currentThread().getName() + " - PUT: " + userId + " > 1");
        } else {
            var current = hitsPerUser.get(userId).incrementAndGet();

            System.out.println(Thread.currentThread().getName() + " - UPD: " + userId + " > " + current);
        }
    }

    public static void main(String[] args) {

        UserStats stats = new UserStats();

        final List<String> users = generateUsers(10);
        
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                users.forEach(u -> stats.onUserCall(u));
            });
        }
        service.shutdown();
    }

    private static List<String> generateUsers(int count) {
        List<String> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            users.add(UUID.randomUUID().toString());
        }
        return users;
    }
}
