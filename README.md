# Address2Int
This is a tool to transfer ipv4 address to int.
**The function returns `long` instead of `int32` because there is no official unsigned int in Scala.**

# Usage

### requirement
- java 8

### Cmd
Just download the jar from bin file.
```bash
Address2Int 0.1
Usage: java -jar <path-to-your-jar> [options]

  -i, --in <value>  in is the address.
  --help            prints this usage text
```
Exmaple:
```bash
java -jar address2int-assembly-0.1.jar --in 255.255.255.255
```
You can get compiled jar from `bin` dir.

## Development

### requirement
- java 8
- sbt 1.0+

## How to run tests
- run `sbt test`

## How to package a jar
- run `sbt assembly`