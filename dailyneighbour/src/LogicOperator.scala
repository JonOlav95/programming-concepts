class LogicOperator(private var cells: Array[Array[Cell]], private val size: Int) {

  def logic(): Unit = {

    val yRange = 0 until size
    val xRange = 0 until size

    yRange.foreach(y => {
      xRange.foreach(x => {

        if(cells(y)(x).getValue != 0){
          this.checkEndPoints(y, x)
          this.checkCloseNeighbour(y, x)
        }

      })
    })

  }


  private def checkEndPoints(y: Int, x: Int): Boolean = {

    val Size = this.size

    cells(y)(x).getValue match {
      case 1 =>
        cells(y)(x).neighbours.foreach(
          neighbour => (neighbour.setValue(2), neighbour.setChangeable(false))
        )
        true


      case Size =>
        cells(y)(x).neighbours.foreach(
          neighbour => (neighbour.setValue(Size - 1), neighbour.setChangeable(false))
        )
        true

      case _ => false

    }

  }


  private def checkCloseNeighbour(y: Int, x: Int): Boolean = {

    var cellVal = cells(y)(x).getValue

    cells(y)(x).neighbours.foreach(
      neighbour =>
        if(neighbour.getValue == 0 && neighbour.getChangeable){

          if(y == neighbour.getY){



            if(cells(y).exists(x => x.getValue == cellVal - 1)){

              neighbour.setValue(cellVal + 1)
              neighbour.setChangeable(false)
              return true

            }

            else if(cells(y).exists(x => x.getValue == cellVal + 1)){

              neighbour.setValue(cellVal - 1)
              neighbour.setChangeable(false)
              return true

            }



          }

          if(x == neighbour.getX){

            for(i <- 0 until size){

              if(cells(i)(x).getValue == cellVal + 1){
                neighbour.setValue(cellVal - 1)
                neighbour.setChangeable(false)
                return true

              }

              else if(cells(i)(x).getValue == cellVal - 1){
                neighbour.setValue(cellVal + 1)
                neighbour.setChangeable(false)
                return true
              }

            }


          }

        }
    )


    return false
  }

}
