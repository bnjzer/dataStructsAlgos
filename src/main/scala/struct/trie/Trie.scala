package struct.trie

class Trie(list: Seq[String]) {
  private val root = new TrieNode()
  list.foreach(root.addWord)

  def this() = this(List.empty[String])

  def contains(word: String, exact: Boolean): Boolean = {
    var currentNode = root
    var i: Int = 0
    for (i <- 0 until word.length) {
      currentNode = currentNode.getChild(word.charAt(i)) match {
        case Some(node) => node
        case None => return false
      }
    }

    !exact || currentNode.terminates
  }

  def contains(prefix: String): Boolean = contains(prefix, false)

  def getRoot = root
}
