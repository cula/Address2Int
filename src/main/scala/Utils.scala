package scala

import scala.collection.mutable

object Utils {


  private val SEP = '.'
  private val SPACE = ' '
  private val VALID_SEP_COUNT = 3


  private def isSep(s: Char): Boolean = s == SEP
  private def isSpace(s: Char): Boolean = s == SPACE
  private def isNumber(s: Char): Boolean = s <= '9' && s >= '0'

  def address2Int(address: String): Long = {
    if (address == null)
      throw InvalidAddressException("Address should not be null.")
    var result = 0L
    var tmpDigitStore = mutable.Buffer[Char]()
    var sepCount = 0
    for (i <- 0 until address.length) {
      val c = address(i)
      if (isSep(c)) {
        sepCount += 1
        if (sepCount > VALID_SEP_COUNT)
          throw InvalidAddressException("There should be exact 3 '.' in the address.")
        if (tmpDigitStore.isEmpty)
          throw InvalidAddressException("Address should not start with '.' or contain any '..'.")
        val num = tmpDigitStore.foldLeft("")({(z, r) => z + r}).toInt
        if (num > 255)
          throw InvalidAddressException("Address should only contain numbers between 0 (included) and 255 (included).")
        result = (result << 8) + num
        tmpDigitStore = mutable.Buffer[Char]()
      } else if (isSpace(c)) {
        if (i != 0 && i != address.length - 1 && isNumber(address(i - 1)) && isNumber(address(i + 1)))
          throw InvalidAddressException("Address should have not any space between two digits.")
      } else if (isNumber(c)) {
        tmpDigitStore += c
        if (tmpDigitStore.length > 3)
          throw InvalidAddressException("Address should not contain any number more than 255.")
      } else {
        throw InvalidAddressException("Address should only contain digits, spaces, or '.'.")
      }
    }
    if (sepCount != 3)
      throw InvalidAddressException("There should be exact 3 '.' in the address.")
    if (tmpDigitStore.isEmpty)
      throw InvalidAddressException("Address should not end with '.'")
    result = (result << 8) + tmpDigitStore.foldLeft("")({(z, r) => z + r}).toInt
    result
  }
}
