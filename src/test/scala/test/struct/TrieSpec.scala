package test.struct

import struct.trie.Trie
import test.UnitSpec

class TrieSpec extends UnitSpec {
  "A trie" should {
    val words = List("hello", "world")
    val trie = new Trie(words)

    "return true" when {
      "an added word is requested" in {
        trie.contains("hello") shouldBe true
      }
    }

    "an existing prefix is requested" in {
      trie.contains("hel") shouldBe true
    }

    "return false" when {
      "an absent word is requested" in {
        trie.contains("foo") shouldBe false
      }
    }

    "an existing prefix is requested but with exact set to true" in {
      trie.contains("hel", true) shouldBe false
    }
  }
}
