Name: Amaya Keys

Part 1:

Shared Resource #1: nextId, which is an integer counter used to generate unique request IDs
Shared Resource #2: requests, which is an ArrayList that stores all submitted service requests

Concurrency Problem: One concurrency problem that may occur is a race condition on nextId. getNextId() is a read-modify-write operation that if executed simultaneously by two threads, can cause two students to receive duplicated IDs and break uniqueness. Both can possibly read the same value of nextId before the other increments it first. A similar condition can occur for requests. If multiple threads call requests.add() at the same time, the internal array can be resized and written to at the same time. This may cause lost updates or a corrupted internal state.

Why addRequest() is unsafe: It enacts a check-then-act operation that does not maintain the atomicity property. Between any of the three steps of getting the ID, building the string, and adding to the list, a thread can be preempted. This means its possible for entries to arrive out of order or with duplicated IDs if another thread already called getNextId() between steps 1 and 3. In other words, there's no guarantee that ID generation and list generation will be atomic.


Part 2:

Fix A: This works places a monitor lock on both methods, meaning only one thread can hold that lock at a time. Since they now share the same lock, the entire sequence inside addRequest() becomes mutually exclusive. This works because:
1) The read-modify-return inside getNextId() is now atomic and
2) Only 1 thread can call requests.add at a time

Fix B: This works because -
1) AtomicInteger uses low-level CPU instructions to make the read-modify-write cycle on nextId indivisible without needing a lock. Now no two threads can get the same aID because calling getAndIncrement() is guaranteed to be atomic.
2) CopyOnWriteArrayList makes a fresh copy of the underlying array on every write, meaning concurrent reads are never blocked and writes can't corrupt each other.

Fix C: This does not work because -
1) Locking getRequests() doesn't prevent two threads from racing on nextId or concurrently calling requests.add(). Those operations remain unguarded.
2) It exposes the internal list directly so even if reads are momentarily locked, the method hands back a reference to the actual ArrayList. This means .add(), .remove(), or other mutations can still be called on it outside of a lock.

Part 3:

Based on Arthur Riel's heuristics, getNextId() should be private not public. The relevant heuristic states to "keep the interface of a class as minimal as possible, exposing only what outside clients genuinely need". Exposing it to the client would allow them to bypass the workflow and generate an ID independently, as well as increases the likelihood of concurrency problems due to mutual exclusion being broken. Making it private ensures ID generation and request insertion are done together.

Part 4:

Description: The java.util.concurrent package provides thread-safe data structures that handle synchronization internally and doesn't require the synchronized keyword. The unsafe shared resources can be replaced with concurrent-safe ones that guarantee correctness without the need for manual locking. AtomicINteger makes the ID increment atomically at the hardware level and CopyOnWriteArrayList makes each write to requests thread-safe by operating on a fresh copy of the array.


Code Snippet:

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

```
public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private List<String> requests = new CopyOnWriteArrayList<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        requests.add("Request-" + id + " from " + studentName);
    }
}
```





