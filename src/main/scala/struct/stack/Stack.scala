package struct.stack

class Stack[E](capacity: Int) {
  var top: Node[E] = _
  var bottom: Node[E] = _
  var size = 0

  def isFull = capacity == size

  def isEmpty = size == 0

  def push(v: E) = {
    if (size >= capacity) throw new FullStackException
    size += 1
    val n = new Node(v)
    if (size == 1) bottom = n
    join(n, bottom)
    top = n
  }

  def pop(): E = {
    if (isEmpty) throw new EmptyStackException

    val v = top.value
    size -= 1
    top = top.below
    join(null, top)
    v
  }

  def join(above: Node[E], below: Node[E]) = {
    if (above != null) above.below = below
    if (below != null) below.above = above
  }

  def removeBottom(): E = {
    if (isEmpty) throw new EmptyStackException
    val v = bottom.value
    bottom = bottom.above
    if (bottom != null) bottom.below = null
    size -= 1
    v
  }
}

private[stack] class Node[E](val value: E) {
  var below: Node[E] = _
  var above: Node[E] = _
}
