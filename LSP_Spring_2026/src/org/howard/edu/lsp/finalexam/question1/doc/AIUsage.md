AI Tools Used:
Claude

Prompts Used (2–5 max):
1. Given the following background and given code, can you help me identify 2 shared resources, what concurrency problems may occur, and why addRequest() may be unsafe?

Background
A university system stores student service requests. Each request should receive a unique request ID before being added to a shared list. Multiple requests may be processed at the same time.

Given Code
import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private int nextId = 1;
    private List<String> requests = new ArrayList<>();

    public int getNextId() {
        int id = nextId;
        nextId++;
        return id;
    }

    public void addRequest(String studentName) {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    }

    public List<String> getRequests() {
        return requests;
    }
}

2. Explain why those two fixes work (individually) as well as why this fix does not work: public synchronized List<String> getRequests() { ... }

3. Based on Arthur’s Riel’s heuristics, should getNextId() be public? Explain briefly but clearly.

4. 1. Briefly describe how an alternative approach to managing concurrency that does not rely on the synchronized keyword could be used to make addRequest() thread-safe.  2.	Provide a short code snippet (5–10 lines) demonstrating how this approach would be applied to make addRequest()thread-safe.

How AI Helped (2–3 sentences):
AI helped to pinpoint the specific root of the concurrency problems within the given code, allowing me to gain a more targeted understanding of the cause and associated dangers. Given those, it was then able to help me reason with what potential options I have for fixing these problems.

Reflection (1–2 sentences):
I learned how the concurrency problems covered in lecture can show up in real-world applications, even those that may appear quite simple and straight-forward.
