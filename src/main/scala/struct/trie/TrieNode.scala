package struct.trie

import scala.collection.mutable

class TrieNode(val c: Character) {
  private val children = mutable.Map.empty[Char, TrieNode]
  private var boolTerminates = false

  def this() = this('\0')

  def getChar = c

  def addWord(word: String): Unit = {
    if (word != null && !word.isEmpty) {
      val firstChar = word.charAt(0)

      val child = getChild(firstChar) match {
        case Some(node) => node
        case None => val newChild = new TrieNode(firstChar)
          children.put(firstChar, newChild)
          newChild
      }

      if (word.length > 1)
        child.addWord(word.substring(1))
      else
        child.setTerminates(true)
    }
  }

  def getChild(char: Char): Option[TrieNode] = children.get(char)

  def terminates = boolTerminates

  def setTerminates(term: Boolean) = boolTerminates = term
}
