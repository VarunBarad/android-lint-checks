# Android Lint Checks

This is a sample android application project which shows the use of library which implements some custom lint checks that I find useful.

## Adding library to project

In your module-level `build.gradle` file (generally `<project-dir>/app/build.gradle` add the following dependency

```groovy
dependencies {
    lintChecks "com.varunbarad:android-lint-checks:0.3.0"
    ...
}
```

## Run the lint check

In your project directory, run the following command

```shell
./gradlew lint
```

This will run the lint tool on your complete project, including any custom lint-checks you may have added or imported.

## Running lint check before every app assemble step

If you want to run the lint checks every time you hit run in your Android studio, include the below block inside your `android` block

```groovy
android {
    applicationVariants.all { variant ->
        variant.outputs.each { output ->
            def lintTask = tasks["lint${variant.name.capitalize()}"]
            output.assemble.dependsOn lintTask
        }
    }
    ...
}
```
## To-Do

1. Check for constraint-layout children not visible due to misalignment (example: `app:layout_constraintEnd_toStartOf="parent"`)
