package struct

class LinkedListNode(val data: Int, var prev: LinkedListNode, var next: LinkedListNode) {
  var last: LinkedListNode = _

  def this(data: Int) = this(data, null, null)

  def this() = this(-1, null, null)

  def setNext(n: LinkedListNode): Unit = {
    next = n
    if (last == this)
      last = next
    if (next != null && next.prev != this)
      next.setPrevious(this)
  }

  def setPrevious(p: LinkedListNode): Unit = {
    prev = p;
    if (prev != null)
      prev.setNext(this)
  }

  override def clone: LinkedListNode = {
    var nextBis: LinkedListNode = null
    if (next != null)
      nextBis = next.clone
    new LinkedListNode(data, nextBis, null)
  }
}
