package flip.pdf

import org.scalacheck.Gen
import org.specs2.mutable._
import org.specs2.ScalaCheck
import flip.conf.SketchConfGen
import flip.measure.MeasureGen

class RecurSketchSpec extends Specification with ScalaCheck {

  "PeroidicSketch" should {

    "rearranged" in {

      todo

//      (for {
//        sketch <- PeriodicSketchGen.periodicSketchSample
//        utdSketch <- PeriodicSketch.primUpdate(sketch, 1.0)
//        rearranged <- PeriodicSketch.rearrange(utdSketch)
//      } yield rearranged)
//        .fold(ko)(sketch => ok)
    }

  }

}

object RecurSketchGen {

//  def intRecurSketchGen: Gen[RecurSketch[Int]] = for {
//    measure <- MeasureGen.intMeasureGen
//    conf <- SketchConfGen.sketchConfGen
//  } yield RecurSketch.empty(measure, conf)
//
//  def recurSketchSample: Option[RecurSketch[Int]] = intRecurSketchGen.sample

}