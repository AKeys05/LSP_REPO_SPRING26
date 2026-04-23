Name: Amaya Keys

Part 1: Shared Resources and Risk

1) The two shared resources are nextId, which is an integer counter used to generate unique request IDs, and requests, which is an ArrayList that stores all submitted service requests. 

2) One concurrency problem that may occur is a race condition on nextId. getNextId() is a read-modify-write operation that if executed simultaneously by two threads, can cause two students to receive duplicated IDs and break uniqueness. Both can possibly read the same value of nextId before the other increments it first.

A similar condition can occur for requests. If multiple threads call requests.add() at the same time, the internal array can be resized and written to at the same time. This may cause lost updates or a corrupted internal state.

3) addRequest() is unsafe because it enacts a check-then-act operation that does not maintain the atomicity property. Between any of the three steps of getting the ID, building the string, and adding to the list, a thread can be preempted. This means its possible for entries to arrive out of order or with duplicated IDs if another thread already called getNextId() between steps 1 and 3. In other words, there's no guarantee that ID generation and list generation will be atomic.
