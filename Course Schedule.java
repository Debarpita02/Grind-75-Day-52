import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the course dependencies
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Create an array to store the in-degrees of each course
        int[] inDegrees = new int[numCourses];

        // Build the adjacency list and in-degrees array
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int dependency = prereq[1];
            adjList.get(dependency).add(course); // Reverse the edge direction
            inDegrees[course]++;
        }

        // Initialize a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Add courses with in-degree 0 to the queue (no prerequisites)
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // Initialize a counter for visited courses
        int visitedCount = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visitedCount++;

            for (int dependentCourse : adjList.get(course)) {
                inDegrees[dependentCourse]--;
                if (inDegrees[dependentCourse] == 0) {
                    queue.offer(dependentCourse);
                }
            }
        }

        // Check if all courses can be finished (no cycles)
        return visitedCount == numCourses;
    }
}
