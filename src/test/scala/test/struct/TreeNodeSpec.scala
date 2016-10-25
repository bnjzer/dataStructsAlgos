package test.struct

import struct.TreeNode
import test.UnitSpec

class TreeNodeSpec extends UnitSpec {

  "a binary search tree" when {
    "being added nodes" should {
      "be constructed correctly" in {
        val root = new TreeNode(7)
        root.insertInOrder(3)
        root.insertInOrder(1)
        root.insertInOrder(4)
        root.insertInOrder(11)
        root.insertInOrder(9)
        root.insertInOrder(15)
        root.insertInOrder(8)
        root.toString.trim shouldBe "1 3 4 7 8 9 11 15"
        root.getSize shouldBe 8
        root.find(2) shouldBe None
        root.find(8).get.data shouldBe 8
      }
    }
  }
}
