import java.util.*;

public class QuizData {

    private static final Map<String, List<Question>> subjectQuestions = new HashMap<>();

    static {
        subjectQuestions.put("Data Structure", Arrays.asList(
            new Question("What is a Stack?", Arrays.asList("FIFO", "LIFO", "Random"), "LIFO"),
            new Question("Which data structure uses FIFO?", Arrays.asList("Queue", "Stack", "Graph"), "Queue"),
            new Question("What is the time complexity of binary search?", Arrays.asList("O(n)", "O(log n)", "O(1)"), "O(log n)"),
            new Question("Which sorting algorithm is fastest on average?", Arrays.asList("Bubble Sort", "Quick Sort", "Selection Sort"), "Quick Sort"),
            new Question("Which data structure is used for recursion?", Arrays.asList("Queue", "Stack", "Linked List"), "Stack"),
            new Question("What is a linked list?", Arrays.asList("Data stored in contiguous blocks", "Data stored with pointers", "Random access memory"), "Data stored with pointers"),
            new Question("Which data structure is used in BFS?", Arrays.asList("Stack", "Queue", "Tree"), "Queue"),
            new Question("What is the space complexity of an adjacency matrix?", Arrays.asList("O(V^2)", "O(V+E)", "O(1)"), "O(V^2)"),
            new Question("What is a Heap?", Arrays.asList("Tree-based data structure", "Linear data structure", "Graph"), "Tree-based data structure"),
            new Question("Which data structure is best for implementing a priority queue?", Arrays.asList("Array", "Heap", "Stack"), "Heap")
        ));

        subjectQuestions.put("Algorithm", Arrays.asList(
            new Question("What is the worst-case time complexity of Quick Sort?", Arrays.asList("O(n log n)", "O(n^2)", "O(n)"), "O(n^2)"),
            new Question("Which algorithm is used for shortest path?", Arrays.asList("Dijkstra's", "DFS", "Prim's"), "Dijkstra's"),
            new Question("What is dynamic programming?", Arrays.asList("Recursion + Memoization", "Greedy Algorithm", "Brute Force"), "Recursion + Memoization"),
            new Question("Which algorithm is used in Kruskal’s MST?", Arrays.asList("Greedy", "Dynamic Programming", "Divide & Conquer"), "Greedy"),
            new Question("Which algorithm is best for searching?", Arrays.asList("Binary Search", "Linear Search", "Jump Search"), "Binary Search"),
            new Question("What is the recurrence relation for Merge Sort?", Arrays.asList("T(n) = 2T(n/2) + O(n)", "T(n) = T(n/2) + O(n)", "T(n) = T(n-1) + O(1)"), "T(n) = 2T(n/2) + O(n)"),
            new Question("What is the time complexity of Fibonacci using recursion?", Arrays.asList("O(2^n)", "O(n)", "O(n log n)"), "O(2^n)"),
            new Question("Which algorithm is used in Huffman Coding?", Arrays.asList("Greedy", "Dynamic Programming", "Brute Force"), "Greedy"),
            new Question("Which sorting algorithm is best for nearly sorted data?", Arrays.asList("Insertion Sort", "Bubble Sort", "Selection Sort"), "Insertion Sort"),
            new Question("Which algorithm is used for cycle detection in graphs?", Arrays.asList("DFS", "BFS", "Dijkstra's"), "DFS")
        ));

        subjectQuestions.put("Operating System", Arrays.asList(
            new Question("Which of these is an OS?", Arrays.asList("Windows", "Google", "Python"), "Windows"),
            new Question("What is a deadlock?", Arrays.asList("Infinite loop", "Blocked process waiting for resources", "System crash"), "Blocked process waiting for resources"),
            new Question("What does a scheduler do?", Arrays.asList("Manages CPU time", "Controls Memory", "Handles Input/Output"), "Manages CPU time"),
            new Question("What is virtual memory?", Arrays.asList("Extra RAM", "Memory management technique", "CPU Register"), "Memory management technique"),
            new Question("Which scheduling algorithm is preemptive?", Arrays.asList("Round Robin", "FCFS", "SJF"), "Round Robin"),
            new Question("Which memory management technique uses paging?", Arrays.asList("Segmentation", "Swapping", "Virtual Memory"), "Virtual Memory"),
            new Question("What does a process control block store?", Arrays.asList("Process state", "Process code", "Process name"), "Process state"),
            new Question("Which is not a type of OS?", Arrays.asList("Batch", "Distributed", "C++"), "C++"),
            new Question("What is the role of a kernel?", Arrays.asList("Manages system resources", "Runs applications", "Handles email"), "Manages system resources"),
            new Question("Which OS is open source?", Arrays.asList("Windows", "Linux", "MacOS"), "Linux")
        ));
    }

    public static List<Question> getQuestions(String subject) {
        return subjectQuestions.getOrDefault(subject, new ArrayList<>());
    }
}
