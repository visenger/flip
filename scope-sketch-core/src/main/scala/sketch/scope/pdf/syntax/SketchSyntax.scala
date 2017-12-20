package sketch.scope.pdf.syntax

import sketch.scope.cmap.Cmap
import sketch.scope.measure.Measure
import sketch.scope.pdf.{Count, Dist, Prim, Range, Sketch, Structure}
import sketch.scope.pdf.monad.{DistFunctor, SketchMonad}
import sketch.scope.plot.CountPlot

/**
  * Licensed by Probe Technology, Inc.
  */
trait SketchSyntax extends SketchPropSyntax with SketchMonadSyntax

trait SketchPropSyntax {

  implicit class SketchPropSyntaxImpl[A](sketch: Sketch[A]) {
    def sample: (Sketch[A], A) = Sketch.sample(sketch)
    def samples(n: Int): (Sketch[A], List[A]) = Sketch.samples(sketch, n)
    def update(as: A*): Option[Sketch[A]] = Sketch.update(sketch, as.toList.map(a => (a, 1d)))
    def update(as: List[(A, Count)]): Option[Sketch[A]] = Sketch.update(sketch, as)
    def narrowUpdate(as: A*): Option[Sketch[A]] = Sketch.narrowUpdate(sketch, as.toList.map(a => (a, 1d)))
    def deepUpdate(as: A*): Option[(Sketch[A], Structure)] = Sketch.deepUpdate(sketch, as.toList.map(a => (a, 1d)))
    def count(from: A, to: A): Option[Double] = Sketch.count(sketch, from, to)
    def countPlot: Option[CountPlot] = Sketch.countPlot(sketch)
    def sum: Double = Sketch.sum(sketch)
    //    def clear: Sketch = Sketch.clear(sketch)
    def probability(from: A, to: A): Option[Double] = Sketch.probability(sketch, from, to)
    def rearrange: Option[Sketch[A]] = Sketch.rearrange(sketch)
    def caDepth: Int = Sketch.cmapNo(sketch)
    def caSize: Int = Sketch.cmapSize(sketch)
    def coDepth: Int = Sketch.counterNo(sketch)
    def coSize: Int = Sketch.counterSize(sketch)
    def lastCmap: Option[Cmap] = Sketch.lastCmap(sketch)
  }

}

trait SketchMonadSyntax {

  lazy val sketchMonad: SketchMonad[Sketch, Dist, Sketch] = SketchMonad.pointToPoint

  implicit class SketchMonadSyntaxImpl[A](sketch: Sketch[A]) {
    def map[B](f: A => B)(implicit measureB: Measure[B]): Sketch[B] =
      sketchMonad.map(sketch, f, measureB)
    def flatMap[B, S1<:Sketch[_], S2<:Sketch[_]](f: A => Dist[B])(implicit measureB: Measure[B]): Sketch[B] =
      sketchMonad.bind(sketch, f, measureB)
  }

}