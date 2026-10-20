package y2025.d01

import java.io.File

fun main () {
  val (q1, q2) = Regex("([LR])([0-9]*)")
    .findAll(File("src/y2025/d01/input").readText())
    .fold(Triple(0,0,50)) { (q1, q2,curDial),m ->
      val (dir, rawAmt) = m.destructured
      val fullSpins = rawAmt.toInt()/100
      val partSpin = rawAmt.toInt().mod(100)
      val nxtDial = (
        if (dir == "R") curDial+partSpin
        else curDial-partSpin
      )
//      println("$dir$rawAmt - $fullSpins/$partSpin, $curDial => $nxtDial")
      Triple(
        q1
          + if (nxtDial.mod(100)==0) 1 else 0,
        q2
          + fullSpins
          + if (curDial != 0 && (nxtDial !in 1..99)) 1 else 0,
        nxtDial.mod(100)
      )
    }
  println("q1: $q1")
  println("q2: $q2")
}