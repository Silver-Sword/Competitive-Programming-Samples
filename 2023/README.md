# Welcome!

These 20 sample programs are intended to give the reader a good idea of the breadth and depth of my
programming experience in 2023 as a competitive programmer.  This is not a comprehensive list by any means,
but should provide a meaningful understanding of my problem solving and C++ abilities.

# What to Expect

Each sample file has a general format:
```
// Problem Name
// Problem Credits

// Problem Description
/*
    <description>
*/

// Solution Description
/*
    <description>
*/

Solution Code
```

The solution programs are generally between 100-200 lines of code and follow the coding standards for competitive programmers.  The general problem and solution ideas can be seen in the Problem Tags section below.

# Problem Tags

| Problem Title | File Name | Problem Tags |
| ------------- | --------- | ------------ |
| Agile Permutations | agile.cpp | matrix exponentiation, counting |
| Among Us | amongus.cpp | graph, bitmask dp, bfs |
| Kiwis vs Kangaroos II | battle.cpp | greedy |
| Two Buildings | buildings.cpp | divide and conquer |
| Checker Slide | checker.cpp | hashing, bfs |
| Electricity | electricity.cpp | greedy |
| Even Substrings | even.cpp | segment tree, prefix sums, hashing |
| Tree Number Generator | generator.cpp | tree, lowest common ancestor |
| Grid | grid.cpp | greedy, geometry |
| AI Jeopardy | jeopardy.java | number theory, binary search |
| Joining the KAK | kak.cpp | counting, string, dp |
| lifeguards    | lifeguards.cpp | dp |
| Open-Pit Mining | openpitmining.cpp | network flow, min cut |
| Password Suspects | passwordsuspect.cpp | string, aho corasick |
| Push a Box | pushabox.cpp | graph, 2VCCs |
| Red/Blue Spanning Trees | spanning.cpp | MST, greedy |
| Standing Out From the Herd  | standingout.cpp | string, suffix array |
| Three Triangles | triangles.cpp | geometry |
| Tri-Color Puzzle | tricolor.cpp | gaussian, counting |
| Word Puzzle | wordpuzzle.cpp | string, dp |

## Acronym Key

| Acronym | Phrase |
| :-----: | ------ |
| 2VCCs | 2 vertex connected components |
| bfs | breadth first search |
| dp | dynamic programming |
| MST | minimum spanning tree |

# Credits

Some of the solutions use code from a reference document (kactl).  Link to the document:  
https://github.com/kth-competitive-programming/kactl/blob/main/kactl.pdf

All C++ solutions utilize part of the starting template from kactl. 
Each of the following programs uses template code from kactl:
 - openpitmining.cpp uses Edmonds Karp
 - passwordsuspect.cpp uses Aho Corasick
 - pushabox.cpp uses connected components 
 - spanning.cpp uses the Union Find data structure
 - standingout.cpp uses the Suffix Array data structure
 - triangles.cpp uses the Point structure
