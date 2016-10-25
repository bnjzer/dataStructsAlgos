package struct.stack

class MultiStack(numberOfStacks: Int, defaultSize: Int) {
  private val infos = (0 to numberOfStacks)
    .map(i => new StackInfo(i * defaultSize, defaultSize))
    .toArray
  private val values = new Array[Int](numberOfStacks * defaultSize)

  private class StackInfo(var start: Int, var capacity: Int) {
    var size: Int = _

    def isWithinStackCapacity(idx: Int): Boolean = {
      if (idx < 0 || idx >= values.length) return false

      val contiguousIndex = if (idx < start) idx + values.length else idx
      val end = start + capacity

      start <= contiguousIndex && contiguousIndex <= end
    }

    def lastElementIndex = adjustIndex(start + size - 1)

    def lastCapacityIndex = adjustIndex(start + capacity - 1)

    def isFull = size == capacity

    def isEmpty = size == 0
  }

  def push(stackNum: Int, value: Int) = {
    if (allStacksAreFull) throw new FullStackException // all stacks are full

    val stack = infos(stackNum)
    if (stack.isFull) expand(stackNum)

    stack.size += 1
    values(stack.lastElementIndex) = value
  }

  def pop(stackNum: Int): Int = {
    val stack = infos(stackNum)
    if (stack.isEmpty) throw new EmptyStackException

    val value = values(stack.lastElementIndex)
    values(stack.lastElementIndex) = 0 // clear
    stack.size -= 1
    value
  }

  def peek(stackNum: Int): Int = {
    val stack = infos(stackNum)
    values(stack.lastElementIndex)
  }

  /**
    * Shift items by one element.
    * if capacity avalaible, stack shrinked by 1 element
    * else shift next stack too
    */
  private def shift(stackNum: Int): Unit = {
    val stack = infos(stackNum)

    if (stack.size == stack.capacity) {
      // stack full => shift next one
      val nextStack = (stackNum + 1) % numberOfStacks
      shift(nextStack)
      stack.capacity += 1
    }

    var idx = stack.lastCapacityIndex
    while (stack.isWithinStackCapacity(idx)) {
      values(idx) = values(previousIndex(idx))
      idx = previousIndex(idx)
    }

    values(stack.start) = 0 // clear because before that the value is the last of the previous stack
    stack.start = nextIndex(stack.start)
    stack.capacity -= 1
  }

  private def expand(stackNum: Int): Unit = {
    shift((stackNum + 1) % numberOfStacks)
    infos(stackNum).capacity += 1
  }

  private def allStacksAreFull = numberOfElements == values.length

  private def numberOfElements = infos.map(_.size).sum

  private def nextIndex(idx: Int) = adjustIndex(idx + 1)

  private def previousIndex(idx: Int) = adjustIndex(idx - 1)

  private def adjustIndex(idx: Int) = ((idx % values.length) + values.length) % values.length // because % can be < 0
}
