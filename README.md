# Android Lint Checks

This is a sample android application project which shows the use of library which implements some custom lint checks that I find useful.

## Adding library to project

In your module-level `build.gradle` file (generally `<project-dir>/app/build.gradle` add the following dependency

```groovy
dependencies {
    lintChecks "com.varunbarad:android-lint-checks:0.2.0"
    ...
}
```

## ToDo

- Add steps in README to show how to use the lint tool
