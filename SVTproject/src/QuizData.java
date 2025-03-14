import java.util.*;

public class QuizData {
    
    private static final List<Subject> subjects = new ArrayList<>();

    static {
        subjects.add(new Subject("Data Structure",
            Arrays.asList(
                "What is a Stack?", 
                "Which data structure uses FIFO?", 
                "What is the time complexity of binary search?", 
                "Which sorting algorithm is fastest on average?", 
                "Which data structure is used for recursion?", 
                "What is a linked list?", 
                "Which data structure is used in BFS?", 
                "What is the space complexity of an adjacency matrix?", 
                "What is a Heap?", 
                "Which data structure is best for implementing a priority queue?"
            ), Arrays.asList(
                Arrays.asList("FIFO", "LIFO", "Random"),
                Arrays.asList("Queue", "Stack", "Graph"),
                Arrays.asList("O(n)", "O(log n)", "O(1)"),
                Arrays.asList("Bubble Sort", "Quick Sort", "Selection Sort"),
                Arrays.asList("Queue", "Stack", "Linked List"),
                Arrays.asList("Data stored in contiguous blocks", "Data stored with pointers", "Random access memory"),
                Arrays.asList("Stack", "Queue", "Tree"),
                Arrays.asList("O(V^2)", "O(V+E)", "O(1)"),
                Arrays.asList("Tree-based data structure", "Linear data structure", "Graph"),
                Arrays.asList("Array", "Heap", "Stack")
            ),Arrays.asList(
                "LIFO", "Queue", "O(log n)", "Quick Sort", "Stack", 
                "Data stored with pointers", "Queue", "O(V^2)", 
                "Tree-based data structure", "Heap"
            )
        ));

        subjects.add(new Subject("Algorithm",
            Arrays.asList(
                "What is the worst-case time complexity of Quick Sort?", 
                "Which algorithm is used for shortest path?", 
                "What is dynamic programming?", 
                "Which algorithm is used in Kruskal’s MST?", 
                "Which algorithm is best for searching?", 
                "What is the recurrence relation for Merge Sort?", 
                "What is the time complexity of Fibonacci using recursion?", 
                "Which algorithm is used in Huffman Coding?", 
                "Which sorting algorithm is best for nearly sorted data?", 
                "Which algorithm is used for cycle detection in graphs?"
            ),
            Arrays.asList(
                Arrays.asList("O(n log n)", "O(n^2)", "O(n)"),
                Arrays.asList("Dijkstra's", "DFS", "Prim's"),
                Arrays.asList("Recursion + Memoization", "Greedy Algorithm", "Brute Force"),
                Arrays.asList("Greedy", "Dynamic Programming", "Divide & Conquer"),
                Arrays.asList("Binary Search", "Linear Search", "Jump Search"),
                Arrays.asList("T(n) = 2T(n/2) + O(n)", "T(n) = T(n/2) + O(n)", "T(n) = T(n-1) + O(1)"),
                Arrays.asList("O(2^n)", "O(n)", "O(n log n)"),
                Arrays.asList("Greedy", "Dynamic Programming", "Brute Force"),
                Arrays.asList("Insertion Sort", "Bubble Sort", "Selection Sort"),
                Arrays.asList("DFS", "BFS", "Dijkstra's")
            ),
            Arrays.asList(
                "O(n^2)", "Dijkstra's", "Recursion + Memoization", "Greedy", "Binary Search", 
                "T(n) = 2T(n/2) + O(n)", "O(2^n)", "Greedy", "Insertion Sort", "DFS"
            )
        ));

        subjects.add(new Subject("Operating System",
            Arrays.asList(
                "Which of these is an OS?", 
                "What is a deadlock?", 
                "What does a scheduler do?", 
                "What is virtual memory?", 
                "Which scheduling algorithm is preemptive?", 
                "Which memory management technique uses paging?", 
                "What does a process control block store?", 
                "Which is not a type of OS?", 
                "What is the role of a kernel?", 
                "Which OS is open source?"
            ),
            Arrays.asList(
                Arrays.asList("Windows", "Google", "Python"),
                Arrays.asList("Infinite loop", "Blocked process waiting for resources", "System crash"),
                Arrays.asList("Manages CPU time", "Controls Memory", "Handles Input/Output"),
                Arrays.asList("Extra RAM", "Memory management technique", "CPU Register"),
                Arrays.asList("Round Robin", "FCFS", "SJF"),
                Arrays.asList("Segmentation", "Swapping", "Virtual Memory"),
                Arrays.asList("Process state", "Process code", "Process name"),
                Arrays.asList("Batch", "Distributed", "C++"),
                Arrays.asList("Manages system resources", "Runs applications", "Handles email"),
                Arrays.asList("Windows", "Linux", "MacOS")
            ),
            Arrays.asList(
                "Windows", "Blocked process waiting for resources", "Manages CPU time", "Memory management technique", 
                "Round Robin", "Virtual Memory", "Process state", "C++", "Manages system resources", "Linux"
            )
        ));
    }

    public static Subject getSubject(String subjectName) {
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(subjectName)) {
                return subject;
            }
        }
        return null; // Return null if the subject is not found
    }
}
