package struct

class TreeNode(val data: Int) {
  var left: TreeNode = _
  var right: TreeNode = _
  var parent: TreeNode = _
  var size = 1

  def getSize = size

  def insertInOrder(d: Int): Unit = {
    if (d <= data) {
      if (left == null)
        setLeftChild(new TreeNode(d))
      else
        left.insertInOrder(d)
    } else {
      if (right == null)
        setRightChild(new TreeNode(d))
      else
        right.insertInOrder(d)
    }
    size += 1
  }

  def find(d: Int): Option[TreeNode] = {
    if (d == data)
      return Some(this)
    else if (d < data) {
      if (left != null)
        return left.find(d)
    } else if (right != null)
      return right.find(d)
    return None
  }

  def setLeftChild(left: TreeNode): Unit = {
    this.left = left
    if (left != null)
      left.parent = this
  }

  def setRightChild(right: TreeNode): Unit = {
    this.right = right
    if (right != null)
      right.parent = this
  }

  override def toString: String = {
    val str = new StringBuilder
    if (left != null) str.append(left.toString)
    str.append(s"$data ")
    if (right != null) str.append(right.toString)
    str.toString
  }
}
