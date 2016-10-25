# Data structures and algorithms in Scala

## Data structures implementation

- [HashMap](src/main/scala/struct/HashMapList.scala) with the value being a `List` (in order to manage the hash collisions)
- [LinkedList](src/main/scala/struct/LinkedListNode.scala)
- [Stack](src/main/scala/struct/stack/Stack.scala)
- [SetOfStacks](src/main/scala/struct/stack/SetOfStacks.scala) : stack divided into fixed-size stacks (like with a stack of plates). We can `pop()` directly at any stack, but only `push()` to the last
- [MultiStack](src/main/scala/struct/stack/MultiStack.scala) : several stacks stored in a single array. While the array is not full we can `pop()` and `push()` in any stack
- [Binary Search Tree](src/main/scala/struct/TreeNode.scala)
- [Trie](src/main/scala/struct/trie/Trie.scala)

## Algorithms

Coming...
