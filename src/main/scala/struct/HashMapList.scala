package struct

import scala.collection.mutable.ArrayBuffer
import scala.collection.{Set, mutable}

class HashMapList[T, E] {
  private var map = mutable.Map.empty[T, ArrayBuffer[E]]

  // add item to the list
  def put(key: T, value: E): Unit = put(key, ArrayBuffer(value))

  // add itemS to the list
  def put(key: T, values: ArrayBuffer[E]): Unit = map.get(key) match {
    case Some(arrayBuffer) => arrayBuffer ++= values
    case None => map += key -> values
  }

  // get items at a key
  def get(key: T): Option[ArrayBuffer[E]] = map.get(key)

  // check if hashmap contains key
  def containsKey(key: T): Boolean = map.contains(key)

  // check if (key, value) exists in the map
  def existsKV(key: T, value: E): Boolean = map.contains(key) && map(key).contains(value)

  // get the list of keys
  def getKeys: Set[T] = map.keySet
}
