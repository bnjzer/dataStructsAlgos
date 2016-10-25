package struct.stack

class SetOfStacks[E](capacity: Int) {
  val stacks = Seq.empty[Stack[E]]

  def getLastStack: Option[Stack[E]] = stacks.lastOption

  def isEmpty = getLastStack.isEmpty || getLastStack.get.isEmpty

  def popAt(idx: Int): E = {
    if (stacks.isEmpty || idx >= stacks.size) throw new EmptyStackException
    shiftLeft(idx, true)
  }

  def shiftLeft(idx: Int, removeTop: Boolean): E = {
    val stack = stacks(idx)
    val removedItem = if (removeTop) stack.pop() else stack.removeBottom()
    if (stack.isEmpty)
      stacks.drop(idx)
    else if (idx < stacks.size - 1) {
      val v = shiftLeft(idx + 1, false)
      stack.push(v)
    }
    removedItem
  }

  def push(v: E) = {
    val last = getLastStack
    if (last.isDefined && !last.get.isEmpty)
      last.get.push(v)
    else {
      val stack = new Stack[E](capacity)
      stack.push(v)
      stacks :+ stack
    }

    def pop(): E = {
      val last = getLastStack
      if (last.isEmpty) throw new EmptyStackException
      val v = last.get.pop()
      if (last.get.isEmpty) stacks.drop(stacks.size - 1)
      v
    }
  }
}
