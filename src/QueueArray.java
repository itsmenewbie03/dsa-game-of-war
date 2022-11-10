import java.util.ArrayList;

public class QueueArray<T> {
    // front and rear variables are initially initiated to
    // -1 pointing to no element that control queue
    private int front = -1, rear = -1;
    // sets max size to 52 since a deck of cards contains 52 cards
    private final int MAX_SIZE = 52;

    // Creating an object of ArrayList class of T type
    private ArrayList<T> queue;

    // creates a constructor
    public QueueArray() {
        this.queue = new ArrayList<>();
    }

    // Inserts element at the front of queue
    void enqueue(T X) {
        // If queue is empty
        if (this.empty()) {
            front = 0;
            rear = 0;
            queue.add(X);
        }

        // If queue is not empty
        else {
            front++;
            if (front > MAX_SIZE) {

                // Mov front pointer to next
                queue.set(front, X);
            } else

                // Add element to the queue
                queue.add(X);
        }
    }

    // Deletes elements from the rear from queue
    T dequeue() {
        // if queue doesn't have any elements
        if (this.empty()) {

            // Display message when queue is already empty
            // System.out.println("Queue is already empty");
            return null;
        }
        // If queue has only one element
        else if (front == rear) {

            // Both are pointing to same element
            T out = queue.remove(front);
            front = rear = -1;
            return out;
        }

        // If queue has more than one element
        else {

            // Increment the rear
            T out = queue.remove(rear);
            front--;
            return out;
        }
    }

    // Checks whether the queue is empty
    boolean empty() {
        // Both are initialized to same value
        // as assigned at declaration means no queue made
        if (front == -1 && rear == -1) {
            return true;
        }
        return false;
    }

    // Print the queue

    @Override
    public String toString() {
        if (this.empty())
            return "";

        String ans = "";

        for (int i = rear; i < front; i++) {
            ans += String.valueOf(queue.get(i)) + "->";
        }

        ans += String.valueOf(queue.get(front));

        return ans;
    }

    // this method will the current size of the queue
    public int size() {
        return queue.size();
    }

    /* getter and setter for queue field */
    public ArrayList<T> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<T> queue) {
        this.queue = queue;
    }

}
