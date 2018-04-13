package scala

case class InvalidAddressException(message: String = "Invalid address.") extends Exception(message)
