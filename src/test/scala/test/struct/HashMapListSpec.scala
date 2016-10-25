package test.struct

import struct.HashMapList
import test.UnitSpec

import scala.collection.mutable.ArrayBuffer

class HashMapListSpec extends UnitSpec {
  val myValue1 = "foo"
  val myValue2 = "bar"
  val myIndex1 = 1
  val myIndex2 = 2
  val nonExistingIndex = 123

  "An empty map" when {
    "having just been initialized" should {
      "have an empty keySet" in {
        val myMap = new HashMapList[Integer, String]
        myMap.getKeys.size shouldBe 0
      }
    }

    "being added one element" should {
      "have one key" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        myMap.getKeys.size shouldBe 1
      }
    }
  }

  "A non empty map" when {
    "being added element(s)" should {
      "have one more key if the key didn't exist" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        val nbKeys = myMap.getKeys.size
        myMap.put(myIndex2, myValue2)
        nbKeys + 1 shouldBe myMap.getKeys.size
      }

      "keep the same number of keys when 1 element is added on an existing key" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        val nbKeys = myMap.getKeys.size
        myMap.put(myIndex1, myValue2)
        nbKeys shouldBe myMap.getKeys.size
      }

      "keep the same number of keys when several elements are added on an existing key" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, "aaaa")
        val nbKeys = myMap.getKeys.size
        myMap.put(myIndex1, ArrayBuffer(myValue1, myValue2))
        nbKeys shouldBe myMap.getKeys.size
      }
    }

    "being queried" should {
      "return None if the key doesn't exist" in {
        val myMap = new HashMapList[Integer, String]
        myMap.get(nonExistingIndex) shouldBe None
      }

      "return the corresponding value (Some) if the key exists with one value" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        myMap.get(myIndex1).get.head shouldBe myValue1
      }
    }

    "return all the corresponding values (Some) if the key exists with several values" in {
      val myMap = new HashMapList[Integer, String]
      myMap.put(myIndex1, ArrayBuffer(myValue1, myValue2))
      val values = myMap.get(myIndex1).get
      values(0) shouldBe myValue1
      values(1) shouldBe myValue2
    }

    "being asked if a key exists" should {
      "return true when it exists" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        myMap.containsKey(myIndex1) shouldBe true
      }

      "return false when it doesn't exist" in {
        val myMap = new HashMapList[Integer, String]
        myMap.containsKey(nonExistingIndex) shouldBe false
      }
    }

    "testing if a key/value pair exists" should {
      "return true when it exists with one value for the key" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        myMap.existsKV(myIndex1, myValue1) shouldBe true
      }

      "return true when it exists with several values for the key" in {
        val myMap = new HashMapList[Integer, String]
        myMap.put(myIndex1, myValue1)
        myMap.put(myIndex1, myValue2)
        myMap.existsKV(myIndex1, myValue1) shouldBe true
      }

      "return false when it doesn't exist" in {
        val myMap = new HashMapList[Integer, String]
        myMap.existsKV(myIndex1, myValue1) shouldBe false
      }
    }
  }
}
