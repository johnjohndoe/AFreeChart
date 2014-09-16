# AFreeChart

AFreeChart is a free charting library for Android(tm) platform.

* AFreeChart is based on [JFreeChart](http://www.jfree.org/jfreechart/) 1.0.13.
* Using AFreeChart, you can make graph/chart application very easily.


![AFreeChart TimeSeries Demo01][TimeSeriesDemo01]


## Usage

### Gradle build

To install the sample application to your device run the following task:

```bash
$ ./gradlew installDebug
```

To deploy the library to your local Maven repository run the following task:

```bash
$ ./gradlew install
```

Then, to use the library in your project add the following to your `build.gradle`:

```groovy
dependencies {
    compile 'org.afree:afreechart:0.0.4'
}
```


## Source

* This is a fork of the [original repository](https://code.google.com/p/afreechart/).


## License

* AFreeChart is licensed under the terms of the GNU Lesser General.


[TimeSeriesDemo01]: gfx/AFreeChart-TimeSeriesDemo01.png
