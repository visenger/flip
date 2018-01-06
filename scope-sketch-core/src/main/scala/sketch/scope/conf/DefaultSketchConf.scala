package sketch.scope.conf

trait DefaultSketchConf
  extends AdaPerSketchConf {

  val delta: Double = 1e-5

  val mixingRatio: Double = 1

  val dataKernelWindow: Double = 1e-5

  val decayFactor: Double = 1

  val startThreshold: Double = 100

  val thresholdPeriod: Double = 100

  val queueSize: Int = 100

  val cmap: UniformCmapConf = CmapConf.uniform(1000, 10, Some(-10000d), Some(10000d))

  val counter: CounterConf = CounterConf.apply(200, 2)

}

object DefaultSketchConf extends DefaultSketchConf
