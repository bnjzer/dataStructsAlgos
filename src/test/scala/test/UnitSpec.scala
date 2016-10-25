package test

import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterEach, Matchers, PrivateMethodTester, WordSpecLike}

trait UnitSpec
  extends WordSpecLike
    with Matchers
    with BeforeAndAfterEach
    with Eventually
    with PrivateMethodTester
