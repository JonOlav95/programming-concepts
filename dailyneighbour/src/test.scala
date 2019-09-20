

object test {

  def main(args: Array[String]): Unit = {

    val string = "--batch_123123\n--batch_222222-\n--batch_asdkokasdj"
    val pattern = """(?s)--batch(.*?)--batch""".r
    pattern.findAllIn(string).matchData foreach {
      m => println(m.group(1))
    }

  }

}
