# 8-Puzzle-Solver
- This is an 8-puzzle solver implemented in Java & JavaFX, CSS for GUI.

## Search Algorithms
### 1- DFS
- is an uninformed search algorithm which start by expanding the deepest node in the frontier first.
    
### 2- BFS
- is an uninformed search algorithm in which the root expanded first then all the successors of the root are expanded next, then their successors and so on.
    
### 3- A*
- is an informed search algorithm which use evaluation function f(n) = g(n) + h(n).
  where g(n) is the cost of the path from the start node to goal, and h(n) is the estimated
  cost of the shortest path.
    
- The heuristic estimated using two different methods:-
    1- Manhattan Distance.
    2- Euclidean Distance. 