import org.scalatest.{FlatSpec, Matchers}


class UtilsSpec extends FlatSpec with Matchers {

  "address2String in Utils" should "be able to transfer valid address to int correctly" in {
    Utils.address2Int("172.168.5.1") should equal (2896692481L)
    Utils.address2Int("255.255.255.255") should equal (4294967295L)
    Utils.address2Int("1.1.1.1") should equal (16843009L)
    Utils.address2Int("0.00.0.0") should equal (0L)
  }


  "address2String in Utils" should "be able to transfer valid address to int when address contains spaces but not between digits" in {
    Utils.address2Int(" 172 .168.5. 1 ") should equal (2896692481L)
  }

  "address2String in Utils" should "throw InvalidAddressException when address contains any space between digits" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.16 8.5.1")
  }


  "address2String in Utils" should "throw InvalidAddressException when address contains invalid char" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.168.5.a")
  }

  "address2String in Utils" should "throw InvalidAddressException when address contains numbers more than 255" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.256.5.1")
  }


  "address2String in Utils" should "throw InvalidAddressException when address contains numbers whose length is more than 3" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.256.1115.1")
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.256.11.1111")
  }

  "address2String in Utils" should "throw InvalidAddressException when address starts with '.'" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int(".172.256.11.1")
  }

  "address2String in Utils" should "throw InvalidAddressException when address ends with '.'" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("172.256.15.1.")
  }


  "address2String in Utils" should "throw InvalidAddressException when address is null" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int(null)
  }

  "address2String in Utils" should "throw InvalidAddressException when address is empty" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("")
  }


  "address2String in Utils" should "throw InvalidAddressException when address contains more than 3 '.'" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("1.1.1.1.1")
  }

  "address2String in Utils" should "throw InvalidAddressException when address contains less than 3 '.'" in {
    an[InvalidAddressException] should be thrownBy Utils.address2Int("1.1.1")
  }
}
